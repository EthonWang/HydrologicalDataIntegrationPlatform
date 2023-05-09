<template>
  <v-container>

    <v-row justify="center">
      <v-col cols="12" md="6" class="d-inline-flex">
        <v-text-field
          v-model="searchText"
          clearable
          placeholder="搜索名称或者关键词..."
          prepend-inner-icon="mdi-magnify"
        />
        <v-btn
          size="large"
          class="ml-4"
          variant="outlined"
          prepend-icon="mdi-send"
          @click="getDataList"
        >
          搜索
        </v-btn>
      </v-col>
    </v-row>

    <p class="text-2xl mb-6 mt-6">
      数据类别
    </p>

    <v-row>
      <v-col>
        <v-chip-group
          v-model="classSelected"
          mandatory
          selected-class="text-primary"
        >
          <v-chip
            elevation="3"
            v-for="(value,key)  in dataClassJson"
            :key="key"
            :value="key"
            @click="handleClassClick(key)"
          >
            {{ value }}
          </v-chip>

        </v-chip-group>
      </v-col>
    </v-row>

    <div class="d-flex align-center">
      <p class="text-xl mb-6 mt-6">
        检索结果
      </p>
      <v-spacer></v-spacer>
      <p class="text-sm mb-6 mt-8">
        共 {{ totalElements }} 条
      </p>
    </div>

    <v-row>
      <v-col
        v-for="item in dataList"
        :key="`${item.id}`"
        md="3"
        sm="6"
        cols="12"
      >
        <v-sheet height="250">
          <v-card
            height="270"
            class="mx-auto"
          >
            <v-card-item style="    padding-bottom: 0;">
              <div class="text-sm-body-1 mb-1 d-flex justify-md-space-between">
                <div style="    display: flex;
    align-items: center;
    height: 60px;">
                  <div style="overflow: hidden;
                      text-overflow: ellipsis;
                      -webkit-box-orient: vertical;
                      display: -webkit-box;
                      -webkit-line-clamp: 2;
                      "
                  >
                    <span>{{ item.name }}</span>
                  </div>
                </div>
                <v-btn
                  v-if="item.remoteDataUrl===''"
                  height="40"
                  variant="text"
                  width="40"
                  icon="mdi-download-outline"
                  @click="downloadData(item.fileWebAddress)"
                ></v-btn>
                <v-btn
                  v-else
                  height="40"
                  variant="text"
                  width="40"
                  icon="mdi-share-variant-outline"
                  @click="downloadData(item.fileWebAddress,item.remoteDataUrl)"
                ></v-btn>
              </div>
              <v-card-subtitle class="pb-1">
                <span style="color: coral;  font-size: small;">{{ dataClassJson[item.dataClass] }}</span>
              </v-card-subtitle>
            </v-card-item>

            <v-card-text style="padding-bottom: 3px;">
              <div style="overflow: hidden;
                            text-overflow: ellipsis;
                            white-space: nowrap;
                            padding-bottom: 3px"
              >
                <v-chip
                  v-for="k in item.keywords.split(',')"
                  :key="k"
                  :value="k"
                  size="x-small"
                  class="mr-1"
                  color="success"
                  variant="outlined"
                >
                  {{ k }}
                </v-chip>
              </div>

              <span style="overflow: hidden;
                          text-overflow: ellipsis;
                          -webkit-box-orient: vertical;
                          display: -webkit-box;
                          -webkit-line-clamp: 3;"
              >{{ item.description }}
              </span>

            </v-card-text>


            <!--                <div class="text-caption">-->
            <!--                  <p style="-webkit-line-clamp: 2;overflow: hidden;-->
            <!--                    display: -webkit-box;    -webkit-box-orient: vertical;">-->
            <!--                    Greyhound divisely hello 12 coldly fonwderfully-->
            <!--                    Greyhound divisely hello coldly fonwderfully-->
            <!--                    Greyhound divisely hello coldly fonwderfully-->
            <!--                  </p>-->
            <!--                </div>-->

            <v-divider></v-divider>
            <v-card-actions class="mt-3">
              <v-btn prepend-icon="mdi-delete-outline"
                     color="primary"
                     rounded="xl"
                     elevation="3"
                     variant="outlined"
                     @click="confirmDelete(item.id)"
                     size="small"
              >
                删除
              </v-btn>
              <v-btn prepend-icon="mdi-magnify"
                     color="primary"
                     rounded="xl"
                     elevation="3"
                     variant="outlined"
                     @click="seeDataMeta(item.metaXmlStr)"
                     size="small"
              >
                查看描述文档
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-sheet>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="12">
        <v-pagination v-model="curPage" :length="totalPages"></v-pagination>
      </v-col>
    </v-row>

    <v-dialog
      v-model="dataDeleteDialog"
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
            @click="deleteDataItem"
          >
            确认
          </v-btn>
          <v-btn
            color="success"
            variant="text"
            @click="dataDeleteDialog = false"
          >
            取消
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog
      v-model="dataMetaDialog"
      width="60%"
    >
      <v-card class="pt-2">
        <CodeMirrorPanel
          v-model=curMetaXmlStr
          height="80vh"
        >
        </CodeMirrorPanel>
        <v-card-actions class="pt-4">
          <v-btn color="primary" variant="elevated" block @click="dataMetaDialog = false">Close Dialog</v-btn>
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
import {onActivated, onMounted, ref, watch} from "vue";
import CodeMirrorPanel from "@/components/CodeMirrorPanel";
import requestApi from "@/api/requestApi";


onMounted(() => {
  getDataList()
  console.log(`the component is now mounted.`)
})

// const dataClass = ref([
//   {label: "全部", value: "all"},
//   {label: "地面高程数据", value: "dem"},
//   {label: "土地利用数据", value: "lands"},
//   {label: "土壤数据", value: "soil"},
//   {label: "植被数据", value: "vegetation"},
//   {label: "地质数据", value: "geology"},
//   {label: "水文地质数据", value: "hydrology"},
//   {label: "气候数据", value: "climate"},
//   {label: "其他", value: "others"}
// ])

let snackbar = ref(false)
let msg = ref("")

const dataClassJson = ref({
    "all": "全部",
    "dem": "地面高程数据",
    "lands": "土地利用数据",
    "soil": "土壤数据",
    "vegetation": "植被数据",
    "geology": "地质数据",
    "hydrology": "水文地质数据",
    "climate": "气候数据",
    "others": "其他"
  }
)


let dataList = ref([])
let dataMetaDialog = ref(false)
// let codeTest = ref(`<?xml version="1.0" encoding="UTF-8"?>
// <Model id="123456789">
//     <Summary>
//         <ModelName>
//             GBHM
//         </ModelName>
//         <Description>
//             GBHM 模型是一个分布式流域水文模型
//         </Description>
//     </Summary>
//     <Inputs>
//         <Input name="inputPresData" description="InputData 1: the pressure data(.zip)."
//             optional="true" dataClass="SpecificClass">
//             <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
//         </Input>
//         <Input name="inputRainData" description="InputData 2: the rain data(.zip)." optional="true"
//             dataClass="SpecificClass">
//             <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
//         </Input>
//         <Input name="inputRhumData" description="InputData 3: the relative humidity data(.zip)."
//             optional="true" dataClass="SpecificClass">
//             <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
//         </Input>
//         <Input name="inputSunData" description="InputData 4: the sun data(.zip)." optional="true"
//             dataClass="SpecificClass">
//             <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
//         </Input>
//         <Input name="inputTempData" description="InputData 5: the temperature data(.zip)."
//             optional="true" dataClass="SpecificClass">
//             <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
//         </Input>
//         <Input name="inputWindData" description="InputData 6: the wind data(.zip)." optional="true"
//             dataClass="SpecificClass">
//             <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
//         </Input>
//         <Input name="inputLaiData" description="InputData 7: the lai 叶面积指数 data(.zip)." optional="true"
//             dataClass="SpecificClass">
//             <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
//         </Input>
//
//         <Input name="inputStatusData" description="InputData 8: the status data(.nc)."
//             optional="true" dataClass="SpecificClass">
//             <SpecificClass dataType="NetCDF" description="无需压缩" suffixName=".nc" />
//         </Input>
//
//         <Input name="inputTimeSpanData" description="时间间隔参数" optional="false"
//             dataClass="CustomClass">
//             <CustomClass dataType="Text" description="时间间隔参数xml格式组织" suffixName=".xml">
//                 <CustomSingleRow rowId="1" fillType="Fixed" description="起始行"
//                     value="&lt;Dataset&gt;" />
//                 <CustomSingleRow rowId="2" fillType="HalfFixed" description="模拟开始时间,修改value"
//                     value="&lt;XDO name='simstart' kernelType='real' value='2010 1 1' /&gt;" />
//                 <CustomSingleRow rowId="3" fillType="HalfFixed" description="模拟结束时间,修改value"
//                     value="&lt;XDO name='simend' kernelType='real' value='2010 1 5' /&gt;" />
//                 <CustomSingleRow rowId="4" fillType="HalfFixed" description="中间结果输出时间间隔,修改value"
//                     value="&lt;XDO name='histchkp' kernelType='real' value='24' /&gt;" />
//                 <CustomSingleRow rowId="5" fillType="HalfFixed" description="中间结果输出开始时间,修改value"
//                     value="&lt;XDO name='histstart' kernelType='real' value='2010 1 1' /&gt;" />
//                 <CustomSingleRow rowId="6" fillType="HalfFixed" description="中间结果输出结束时间,修改value"
//                     value="&lt;XDO name='histend' kernelType='real' value='2010 1 5' /&gt;" />
//                 <CustomSingleRow rowId="7" fillType="Fixed" description="结束行"
//                     value="/&lt;Dataset&gt;" />
//             </CustomClass>
//         </Input>
//     </Inputs>
//
// </Model>`)
let curMetaXmlStr = ref("")

let pageSize = ref(8)
let curPage = ref(1)
let classSelected = ref("all")
let searchText = ref("")
let totalPages = ref(1)
let totalElements = ref(0)

let dataDeleteDialog = ref(false)
let dataId = ref("");

const getDataList = function () {
  requestApi.getDataList({
    pageSize: pageSize.value,
    page: curPage.value,
    dataClass: classSelected.value,
    searchText: searchText.value,
    asc: false,
    "sortField": "createTime"
  })
    .then(res => {
      if (res.data.code === 0) {
        let data = res.data.data
        dataList.value = data.content
        totalPages.value = data.totalPages
        totalElements.value = data.totalElements
      }
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
}

const handleClassClick = function (val) {
  console.log(val)
  classSelected.value = val
  getDataList()
}

const seeDataMeta = function (val) {
  console.log(val)
  curMetaXmlStr.value = val
  dataMetaDialog.value = true
}

const confirmDelete = function (id) {
  dataDeleteDialog.value = true;
  dataId.value = id;
}

const deleteDataItem = function () {
  dataDeleteDialog.value = false;
  requestApi.deleteDataItem(dataId.value)
    .then(res => {
      if (res.data.code === 0) {
        msg.value = "数据项删除成功";
        snackbar.value = true;
        getDataList()
      }
    })
    .catch(err => {
      console.log(err)
    })
}

const downloadData = function (fileWebAddress, remoteDataUrl) {
  if (fileWebAddress === "") {
    window.open(remoteDataUrl);
  } else {
    window.location.href = "http://172.21.212.14:8001" + fileWebAddress;
    msg.value = "数据下载请求已发送";
    snackbar.value = true;
  }
}


watch(curPage, (newPage, oldPage) => {
  console.log("curPage", newPage, oldPage)
  if (newPage !== oldPage) {
    getDataList()
  }
})


</script>

<style scoped>
.one-line {
  /*overflow:hidden; //超出的文本隐藏*/
  /*text-overflow:ellipsis; //溢出用省略号显示*/
  /*white-space:nowrap; //溢出不换行*/
}

.three-line {

}

</style>
