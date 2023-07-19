<template>

  <v-container style="height: 80vh;">
    <v-row
    >
      <v-col
        cols="3"
      >
        <v-sheet
          color="grey-lighten-4"
          class="pa-4"
          style="height: 80vh"
        >
          <v-avatar
            class="mb-4"
            color="grey-darken-1"
            size="64"
          >
            <v-icon icon="mdi-account-circle" size="52" color="primary"></v-icon>
          </v-avatar>

          <span class="text-h5"> {{ myUserInfo.name }}</span>

          <div style="color: coral;">{{myUserInfo.email}}</div>

        <v-divider  class="mt-2"></v-divider>

        <v-list>
          <v-list-item
            v-for="[icon, text, value] in links"
            :key="value"
            :value="value"
            active-color="primary"
            :active="selectedMenu === value"
            @click="changeMenu(value)"
          >
            <template v-slot:prepend>
              <v-icon>{{ icon }}</v-icon>
            </template>

            <v-list-item-title>{{ text }}</v-list-item-title>
          </v-list-item>
        </v-list>
        </v-sheet>
      </v-col>


      <v-col
        cols="9"
      >
        <v-sheet style="height: 80vh">

          <v-card
            v-if="selectedMenu==='data'"
            style="height: 100%;overflow: auto">
            <template v-slot:title>
              <h3>个人数据</h3>
              <VCol cols="12">
                <div class="text-subtitle-1 text-medium-emphasis">数据上传：</div>
                <v-file-input
                  variant="underlined"
                  :show-size=true
                  density="compact"
                  v-model="userDataUpFile"
                  prepend-icon="mdi-file-upload"
                  :loading="userDataFileLoading"
                  @change="uploadUserDataFile"
                >
                </v-file-input>
              </VCol>
              <v-divider></v-divider>
            </template>

            <v-card
            class="mx-6 mb-3"
            elevation="2"
            v-for="(item,index) in userDataList"
            >
              <v-card-title>{{item.realName}}</v-card-title>

<!--              <v-card-subtitle></v-card-subtitle>-->

              <v-card-text>
                <span class="mr-8">{{item.fileSize}} KB</span>
              </v-card-text>

              <v-card-actions>
                <span>上传时间：{{item.createTime}}</span>
                <v-spacer></v-spacer>

                <v-btn
                  color="primary"
                  variant="outlined"
                  prepend-icon="mdi-download-outline"
                  @click="downloadUserFile(item.fileWebAddress)"
                >
                  下载
                </v-btn>

                <v-btn
                  color="primary"
                  variant="outlined"
                  prepend-icon="mdi-delete-outline"
                  @click="confirmDeleteUserData(item.id)"
                >
                  删除
                </v-btn>
              </v-card-actions>
            </v-card>

          </v-card>
          <v-card
            v-if="selectedMenu==='scriptTask'"
            style="height: 100%;overflow: auto">
            <v-card-title><h3>任务执行记录</h3></v-card-title>
            <v-divider></v-divider>

            <v-card
              class="mx-6 mb-3 mt-1"
              elevation="2"
              v-for="(item,index) in scriptTaskList"
            >
              <v-card-title>{{index+1}} : {{item.name}}</v-card-title>

              <!--              <v-card-subtitle></v-card-subtitle>-->

              <v-card-text>
                <span class="mr-8">状态：{{item.status}}</span>
              </v-card-text>

              <v-card-actions>
                <span>执行时间：{{item.createTime}}</span>
                <v-spacer></v-spacer>

                <v-btn
                  v-if="item.status==='FinishAndSuccess'"
                  color="primary"
                  variant="outlined"
                  prepend-icon="mdi-download-outline"
                  @click="downloadResultFile(item.fileWebAddress)"
                >
                  下载
                </v-btn>

<!--                <v-btn-->
<!--                  color="primary"-->
<!--                  variant="outlined"-->
<!--                  prepend-icon="mdi-delete-outline"-->
<!--                  @click="confirmDeleteUserData(item.id)"-->
<!--                >-->
<!--                  删除-->
<!--                </v-btn>-->
              </v-card-actions>
            </v-card>

          </v-card>
          <v-card
            v-if="selectedMenu==='message'"
            style="height: 100%;overflow: auto">
            <v-card-title>
              <h3>消息</h3>
              <v-switch
                v-model="socketConnected"
                @click="changeSocketConnected"
                :label="`${socketConnected}`"
                true-value="连接中"
                false-value="断开连接"
                color="success"
                hide-details
              ></v-switch>

            </v-card-title>
            <v-divider></v-divider>

            <div class="d-flex align-center">
              <v-select
                v-model="sendMsgSelected"
                :items="sendMsgList"
                item-title="label"
                item-value="value"
                label="选择发送消息"
                return-object
              ></v-select>
              <v-btn size="large" @click="sendMsg">发送</v-btn>
            </div>

            <div style="height: 68%;overflow-y: scroll" class="d-flex flex-column">
              <v-chip
                class="ma-2"
                size="x-large"
                v-for="msg in msgList"
                style="min-height: 40px;"
              >
                {{msg}}
              </v-chip>
            </div>
          </v-card>

        </v-sheet>
      </v-col>
    </v-row>

    <v-dialog
      v-model="userDataDeleteDialog"
      persistent
      width="auto"
    >
      <v-card>
        <v-toolbar
          color="error"
          title="删除"
        ></v-toolbar>
        <v-card-text>是否删除选中数据条目？</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="error"
            variant="text"
            @click="deleteUserFile"
          >
            确认
          </v-btn>
          <v-btn
            color="success"
            variant="text"
            @click="userDataDeleteDialog = false"
          >
            取消
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-snackbar
      v-model="snackbar"
      :timeout="2000"
    >
      {{ msg }}

      <template v-slot:actions>
        <v-btn
          color="warning"
          variant="text"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script setup>

import {onMounted, ref, watch} from "vue";
import requestApi from "@/api/requestApi";
import PubSub from "pubsub-js";
import {socket,state} from "@/socket";

let selectedMenu = ref('data')

let myUserInfo = ref({})
let cards = ref(['Today', 'Yesterday'])
let drawer = ref(null)
let links = ref([
  ['mdi-inbox-arrow-down', '我的数据','data'],
  ['mdi-calendar-check', '我的任务','scriptTask'],
  ['mdi-send', '我的收藏','collection'],
  ['mdi-message-fast', '我的消息','message']

])
onMounted(() => {
  myUserInfo.value = JSON.parse(localStorage.getItem("loginFlag"))
  getUserDataList(myUserInfo.value.id)
  PubSub.subscribe("scriptRunFinish",()=>{
    getScriptTaskList(myUserInfo.value.id)
  })

  // if(state.connected) {
  //   socketConnected.value = "连接中"
  // }else {
  //   socketConnected.value = "断开连接"
  // }
  console.log(state.value.connected)
})

const userDataList=ref([])
const getUserDataList=(userId)=>{
  requestApi.getUserDataList(userId)
    .then(res=>{
      console.log(res)
      userDataList.value=res.data.data
    })
    .catch(err=>{
      console.log(err)
    })
}


const createUserData=(data)=>{
  requestApi.createUserData(data)
    .then(res=>{
      getUserDataList(myUserInfo.value.id)
      console.log(res)
  })
    .catch(err=>{
    console.log(err)
  })
}

let userDataUpFile = ref([])
let userDataFileLoading = ref(false)
let userDataCreateInfo=ref({
  fileSize: "",
  fileWebAddress: "",
  realName: "",
  storeName: "",
  userId: myUserInfo.value.id
})


const uploadUserDataFile=(file)=>{
  userDataFileLoading.value=true
  let formData = new FormData()
  formData.append("upFile", userDataUpFile.value[0])

  requestApi.uploadDataFile(formData)
    .then(res=>{
      if(res.data.code===0){
        console.log(res.data)
        userDataCreateInfo.value=res.data.data
        userDataCreateInfo.value.userId=myUserInfo.value.id
        createUserData(userDataCreateInfo.value)
        userDataFileLoading.value=false
        msg.value = "数据上传成功";
        snackbar.value = true;
      }else{
        msg.value = "数据上传失败";
        snackbar.value = true;
      }
    })
    .catch(err=>{
      console.log(err)
      userDataFileLoading.value=false
    })
}

let userDataDeleteDialog=ref(false)
let userDataDeleteId=ref('')

const confirmDeleteUserData=(dataId)=>{
  userDataDeleteId.value=dataId
  userDataDeleteDialog.value=true
}

const deleteUserFile=()=>{
  userDataDeleteDialog.value=false
  requestApi.deleteUserData(userDataDeleteId.value)
    .then(res=>{
      console.log(res)
      getUserDataList(myUserInfo.value.id)
      msg.value = "数据删除成功";
      snackbar.value = true;
    })
    .catch(err=>{
      console.log(err)
    })
}

const downloadUserFile=(fileWebAddress)=>{
  window.location.href = "http://172.21.212.14:8001" + fileWebAddress;
  msg.value = "数据下载请求已发送";
  snackbar.value = true;
}


let scriptTaskList=ref([])


const getScriptTaskList=(userId)=>{
  requestApi.getScriptTaskList(userId)
    .then(res=>{
      if(res.data.code===0){
        scriptTaskList.value=res.data.data.reverse()
      }
      console.log(res)
    })
    .catch(err=>{
      console.log(err)
    })
}


const downloadResultFile=(fileWebAddress)=>{
  window.location.href = "http://172.21.212.14:8001" + fileWebAddress;
  msg.value = "数据下载请求已发送";
  snackbar.value = true;
}

let snackbar = ref(false)
let msg = ref('')

const changeMenu=(val)=>{
  selectedMenu.value=val

  if(val==='data'){
    getUserDataList(myUserInfo.value.id)
  }else if(val==='scriptTask'){
    getScriptTaskList(myUserInfo.value.id)
  }

}

//socket
const socketConnected=ref("断开连接")

const changeSocketConnected=(val)=>{
  if(socketConnected.value=="断开连接"){
    socket.connect()
  }else{
    socket.disconnect()
  }
}

const sendMsgSelected=ref()
const sendMsgList=ref([
  {label: '系统信息',value:"server-socket-system-info"},
  {label: '系统功能',value:"server-socket-system-feature"}

])

const msgList=ref([])
const isLoading=ref(true)
const sendMsg=()=>{

  msgList.value.push("我:   "+sendMsgSelected.value["label"])
  socket.emit(sendMsgSelected.value["value"], "hello",() => {
    isLoading.value = false;
  })
}


watch(state.value.connected, (newState,oldVal) => {
    if(newState) {
      socketConnected.value = "连接中"
    }else {
      socketConnected.value = "断开连接"
    }
  },
  {
    immediate:true,
    deep:true
  }
)

watch(state.value.recalls,(newVal,oldVal) => {
    const temp=newVal[newVal.length-1]
    if(temp){
      msgList.value.push("系统:   "+temp.toString())
      console.log(msgList.value)
    }
  },
  {
    immediate:true,
    deep:true
  }
)


</script>
