<template>

  <v-container>
    <h1>数据处理方法</h1>

<!--    <div class="d-flex">-->
<!--      <div v-for="i in 250"  class="d-flex">-->
<!--        <div style="border:2px solid blue; width: 1px;">-->
<!--        </div>-->
<!--        <div style="border:1px solid red; width: 1px;">-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->

    <v-row class="mt-1">
      <v-col
        cols="12"
        md="4"
      >
        <v-sheet rounded="lg"
                 max-width="400"
                 class="mx-auto px-4 py-4"
                 style="height: 80vh;overflow: auto"
                 elevation="4">
<!--          <v-icon icon="mdi-tools"></v-icon>-->
<!--          <h4 class="text-h5 font-weight-bold mb-2 pt-2" style=" text-align: center;">-->
<!--            脚本列表-->
<!--          </h4>-->
          <div class="mb-2 d-flex justify-sm-center">
            <v-btn prepend-icon="mdi-tools" variant="text" size="x-large" color="on-surface" >
              <span class="text-h5 font-weight-bold">脚本列表</span>
            </v-btn>
          </div>

          <v-card
            color="#1F7087"
            theme="dark"
            class="text-white mb-4"
            v-for="(item,index) in scriptList"
          >
            <v-card-title class="text-h5">
              {{item.name}}
            </v-card-title>

            <v-card-text>
              <span>{{item.description}}</span>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                      prepend-icon="mdi-arrow-collapse-all"
                      variant="outlined"
                      color="on-primary"
                      @click="invokeDataMethod(index)"
              >
                调用
              </v-btn>
            </v-card-actions>
          </v-card>

        </v-sheet>


      </v-col>

      <v-col cols="8"
             v-if="curScriptIndex===-1"
             class="d-flex justify-center align-center"
      >
        <span>
            请选择脚本方法执行
        </span>
      </v-col>

      <v-col
        cols="12"
        md="8"
        v-else
      >
        <v-sheet rounded="lg"
                 class="mx-auto px-4 py-4"
                 style="height: 80vh;overflow: auto"
                 elevation="4">
          <h4 class="text-h5 font-weight-bold mb-2 pt-2" style=" text-align: center;">
            {{curScript.name}}
          </h4>

          <h4 class="text-h6 font-weight-bold mb-2 pt-2" >
            输入参数：
          </h4>

          <div
                v-for="(item,index) in curScript['arguments']"
               class="mb-3"
          >

            <v-row>

              <v-col
                cols="10"
                class="pb-0"
              >
                <strong>参数{{index+1}}: </strong>{{item.description}}
              </v-col>

              <v-col
                cols="10"
                class="pt-1"
              >
                <VTextField
                  v-model="curScriptArgs[index].name"
                />
              </v-col>
              <v-col v-if="item.type==='FILE'" cols="2">
                <v-btn icon="mdi-database-search" size="small"  class="mr-1"
                       @click="openDataHubSelect(index)"
                ></v-btn>

                <v-btn icon="mdi-account"  size="small"
                       @click="openUserFileSelect(index)"
                ></v-btn>
              </v-col>


            </v-row>

          </div>
          <v-divider class="mb-4"></v-divider>

          <div class="text-end">
            <v-btn
              prepend-icon="mdi-gamepad-up"
              color="success"
              @click="runScript"
            >
              运行
            </v-btn>
          </div>


        </v-sheet>
      </v-col>
    </v-row>


    <v-dialog
      v-model="dataHubDialog"
      scrollable
      width="auto"
    >
      <v-card width="400">
        <v-card-title>从数据仓库中选择数据</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-radio-group
            v-model="fileSelectedIndex"
            column
          >
            <v-radio
              v-for="(item,index) in dataHubList"
              :label="item.name"
              :value="index"
            ></v-radio>

          </v-radio-group>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="mt-2"
            variant="outlined"
            @click="cancelDataHubSelect"
          >
            取消
          </v-btn>
          <v-btn
            class="mt-2"
            variant="outlined"
            @click="confirmDataHubSelect"
          >
            确定
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>


    <v-dialog
      v-model="userFileDialog"
      scrollable
      width="auto"
    >
      <v-card width="400">
        <v-card-title>从个人中心选择数据</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-radio-group
            v-model="userFileSelectedIndex"
            column
          >
            <v-radio
              v-for="(item,index) in userFileList"
              :label="item.realName"
              :value="index"
            ></v-radio>
          </v-radio-group>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="mt-2"
            variant="outlined"
            @click="cancelUserFileSelect"
          >
            取消
          </v-btn>
          <v-btn
            class="mt-2"
            variant="outlined"
            @click="confirmUserFileSelect"
          >
            确定
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

<!--    <v-btn @click="createSeeConnect()">-->
<!--      test-->
<!--    </v-btn>-->

  </v-container>

</template>

<script setup>

import requestApi from "@/api/requestApi";
import {onMounted, ref} from "vue";
import PubSub from "pubsub-js";

let myUserInfo = ref({})

onMounted(() => {
  getScriptList()
  myUserInfo.value = JSON.parse(localStorage.getItem("loginFlag"))

})

let snackbar = ref(false)
let msg = ref("")


let scriptList = ref([])

let curScriptIndex=ref(-1)
let curScript=ref(null)
let curScriptArgs=ref([])

let scriptCommit=ref({
  scriptId:"",
  userId:"",
  arguments:[]
})

const invokeDataMethod= (selectedScriptIndex) => {
  curScriptIndex.value=selectedScriptIndex
  curScript.value=scriptList.value[selectedScriptIndex]
  curScriptArgs.value=JSON.parse(JSON.stringify(curScript.value.arguments) )
}

const getScriptList = function () {
  requestApi.getScriptList()
    .then(res => {
      if (res.data.code === 0) {
        scriptList.value = res.data.data
      }
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
}

let dataHubList=ref([])
let dataHubDialog=ref(false)
let fileSelectedIndex=ref(0)
let currentFileInputIndex=ref(-1)

const openDataHubSelect=(index)=>{
  getDataHubList()
  dataHubDialog.value=true
  currentFileInputIndex.value=index
}

const getDataHubList=()=>{
  requestApi.getDataListProcess()
    .then(res => {
      if (res.data.code === 0) {
        dataHubList.value = res.data.data
      }
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
}

const confirmDataHubSelect=()=>{
  dataHubDialog.value=false
  curScriptArgs.value[currentFileInputIndex.value]=dataHubList.value[fileSelectedIndex.value]
  curScriptArgs.value[currentFileInputIndex.value].type="FILE"
}

const cancelDataHubSelect=()=>{
  dataHubDialog.value=false
}


let userFileList=ref([])
let userFileDialog=ref(false)
let userFileSelectedIndex=ref(0)
let currentUserFileInputIndex=ref(-1)

const openUserFileSelect=(index)=>{
  getUserFileList()
  userFileDialog.value=true
  currentUserFileInputIndex.value=index
}

const getUserFileList=()=>{
  requestApi.getUserDataList(myUserInfo.value.id)
    .then(res => {
      if (res.data.code === 0) {
        userFileList.value = res.data.data
      }
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
}

const confirmUserFileSelect=()=>{
  userFileDialog.value=false
  curScriptArgs.value[currentUserFileInputIndex.value]=userFileList.value[userFileSelectedIndex.value]
  curScriptArgs.value[currentUserFileInputIndex.value].name=userFileList.value[userFileSelectedIndex.value].realName
  curScriptArgs.value[currentUserFileInputIndex.value].type="FILE"
  console.log("curScriptArgs",curScriptArgs.value)
}

const cancelUserFileSelect=()=>{
  userFileDialog.value=false
}



const runScript=()=>{

  scriptCommit.value.id=curScript.value.id
  for (let i = 0; i < curScriptArgs.value.length; i++) {
    let type=curScriptArgs.value[i].type
    if(type==="FILE"){
      scriptCommit.value.arguments.push({
        type:"FILE",
        value:curScriptArgs.value[i].storeName
      })
    }else{
      scriptCommit.value.arguments.push({
        type:"TEXT",
        value:curScriptArgs.value[i].name
      })
    }
  }
  console.log("scriptCommit",scriptCommit.value)

  let seeId=crypto.randomUUID();
  createSeeConnect(seeId);

  // let temp=sourceMap.value[seeId]
  // while (temp===undefined){
  //   console.log("wait")
  //   temp=sourceMap.value[seeId]
  //   console.log(seeId,temp)
  //   console.log(sourceMap.value)
  // }
  //
  // msg.value = "提交脚本执行"
  // snackbar.value = true
  // requestApi.execScript(scriptCommit.value,seeId)
  //   .then(res => {
  //     console.log(res)
  //   })
  //   .catch(err => {
  //     console.log(err)
  //   })
  //   .finally(()=>{
  //     scriptCommit.value.arguments=[]
  //     closeSeeConnect(seeId)
  //   })
  // scriptCommit.value.arguments=[]


}

const submitRun=(seeId)=>{
  msg.value = "连接成功——提交脚本执行"
  snackbar.value = true

  scriptCommit.value.userId=myUserInfo.value.id
  scriptCommit.value.scriptId=curScript.value.id

  requestApi.execScript(scriptCommit.value,seeId)
    .then(res => {
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
    .finally(()=>{
      PubSub.publish("scriptRunFinish",seeId)
      closeSeeConnect(seeId)
    })
  scriptCommit.value.arguments=[]
}

let sourceMap=ref({})
let count=ref(1);

const createSeeConnect=(seeId)=>{
  // let temp=crypto.randomUUID();
  let source = new EventSource(requestApi.getScriptStatusEvent(seeId));

  source.addEventListener("message", function (e) {
    msg.value = e.data
    snackbar.value = true
    console.log(e.data)
  })

  source.addEventListener('open', function (e) {
    console.log(count.value++)
    console.log("建立连接成功");
    sourceMap.value[seeId]=source;
    submitRun(seeId)
  });

  source.addEventListener('error', function (e) {
      console.log("建立连接失败——未提交脚本执行",e)
      msg.value = "服务器目前繁忙，请稍后重试"
      snackbar.value = true
  });



}

const closeSeeConnect=(seeId)=>{
  sourceMap.value[seeId].close()

  requestApi.closeSeeConnect(seeId)
    .then(res => {
      if(res.data.code===0){
        console.log(--count.value)
        console.log("关闭成功")
      }
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
}


</script>

<style scoped>

</style>
