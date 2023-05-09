<template>
  <v-container>
    <v-row>
      <v-col cols="4">
        <v-sheet rounded="lg">
          <h4 class="text-h5 font-weight-bold mb-2 pt-2" style=" text-align: center;">
            已接入模型
          </h4>
          <v-divider class="my-2"></v-divider>
          <v-card
            max-width="400"
            class="mx-auto px-4 py-4"
            style="height: 80vh;overflow: auto"
            elevation="4"
          >
            <v-row dense>
              <v-col cols="12"
                     v-for="item in modelList"
                     :key="item.id"
                     link
              >
                <v-card
                  color="#007bb6"
                  theme="dark"
                  class="text-white"
                >
                  <v-card-title class="text-h5 ">
                    {{ item.name }}
                  </v-card-title>

                  <v-card-subtitle>
                    <div style="overflow: hidden;
                            text-overflow: ellipsis;
                            white-space: nowrap;">
                      <v-chip
                        v-for="k in item.keywords.split(',')"
                        :key="k"
                        :value="k"
                        size="x-small"
                        class="mr-1"
                        color="on-primary"
                        variant="outlined"
                      >
                        {{ k }}
                      </v-chip>
                    </div>
                  </v-card-subtitle>
                  <v-card-text>
                    <span>{{item.description}}</span>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn prepend-icon="mdi-delete-outline"
                           color="on-primary"
                           variant="text"
                           @click="confirmDelete(item.id)"
                    >
                      删除
                    </v-btn>
                    <v-btn prepend-icon="mdi-magnify"
                           color="on-primary"
                           variant="text"
                           @click="seeModelMeta(item.metaXmlStr)"
                    >
                      查看
                    </v-btn>


<!--                    <v-btn size="small" color="warning"  variant="outlined" icon="mdi-file-search"></v-btn>-->
                  </v-card-actions>
                </v-card>
              </v-col>
            </v-row>
          </v-card>

        </v-sheet>
<!--        <v-btn-->
<!--          class="my-4"-->
<!--          style="width: 100%;"-->
<!--          size="x-large"-->
<!--          color="success"-->
<!--          prepend-icon="mdi-cloud-upload"-->
<!--        >-->
<!--          接入模型-->
<!--        </v-btn>-->
      </v-col>

      <v-col cols="8"
             v-if="curMetaXmlStr===''"
             class="d-flex justify-center align-center"
      >
        <span>
            请选择模型查看描述信息
        </span>
      </v-col>
      <v-col cols="8" v-else>
        <CodeMirrorPanel
          v-model=curMetaXmlStr
          height="89vh"
        >
        </CodeMirrorPanel>
      </v-col>
    </v-row>


    <v-dialog
      v-model="modelDeleteDialog"
      persistent
      width="auto"
    >
      <v-card>
        <v-toolbar
          color="error"
          title="删除"
        ></v-toolbar>
        <v-card-text>是否删除选中模型条目？</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="error"
            variant="text"
            @click="deleteModelItem"
          >
            确认
          </v-btn>
          <v-btn
            color="success"
            variant="text"
            @click="modelDeleteDialog = false"
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
import {onMounted, ref} from "vue";
import CodeMirrorPanel from "@/components/CodeMirrorPanel";
import requestApi from "@/api/requestApi";

onMounted(() => {
  getModelList()
  console.log(`the component is now mounted.`)
})

let snackbar = ref(false)
let msg = ref("")

let modelList = ref([])
let curMetaXmlStr=ref("")

let modelDeleteDialog=ref(false)
let modelId=ref("");

const getModelList = function () {
  requestApi.getModelList()
    .then(res => {
      if (res.data.code === 0) {
        modelList.value = res.data.data
      }
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
}

const confirmDelete=function (id){
  modelDeleteDialog.value=true;
  modelId.value=id;
}

const deleteModelItem= function (){
  modelDeleteDialog.value=false;
  requestApi.deleteModelItem(modelId.value)
    .then(res=>{
      if(res.data.code===0){
        msg.value="模型条目删除成功";
        snackbar.value=true;
        getModelList()
      }
    })
    .catch(err=>{
      console.log(err)
    })
}

const seeModelMeta=(metaXmlStr)=>{
  console.log(metaXmlStr)
  curMetaXmlStr.value=metaXmlStr;
}

let codeTest = ref(`<?xml version="1.0" encoding="UTF-8"?>
<Model id="123456789">
    <Summary>
        <ModelName>
            GBHM
        </ModelName>
        <Description>
            GBHM 模型是一个分布式流域水文模型
        </Description>
    </Summary>
    <Inputs>
        <Input name="inputPresData" description="InputData 1: the pressure data(.zip)."
            optional="true" dataClass="SpecificClass">
            <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
        </Input>
        <Input name="inputRainData" description="InputData 2: the rain data(.zip)." optional="true"
            dataClass="SpecificClass">
            <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
        </Input>
        <Input name="inputRhumData" description="InputData 3: the relative humidity data(.zip)."
            optional="true" dataClass="SpecificClass">
            <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
        </Input>
        <Input name="inputSunData" description="InputData 4: the sun data(.zip)." optional="true"
            dataClass="SpecificClass">
            <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
        </Input>
        <Input name="inputTempData" description="InputData 5: the temperature data(.zip)."
            optional="true" dataClass="SpecificClass">
            <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
        </Input>
        <Input name="inputWindData" description="InputData 6: the wind data(.zip)." optional="true"
            dataClass="SpecificClass">
            <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
        </Input>
        <Input name="inputLaiData" description="InputData 7: the lai 叶面积指数 data(.zip)." optional="true"
            dataClass="SpecificClass">
            <SpecificClass dataType="NetCDF" description="压缩后的nc" suffixName=".zip" />
        </Input>

        <Input name="inputStatusData" description="InputData 8: the status data(.nc)."
            optional="true" dataClass="SpecificClass">
            <SpecificClass dataType="NetCDF" description="无需压缩" suffixName=".nc" />
        </Input>

        <Input name="inputTimeSpanData" description="时间间隔参数" optional="false"
            dataClass="CustomClass">
            <CustomClass dataType="Text" description="时间间隔参数xml格式组织" suffixName=".xml">
                <CustomSingleRow rowId="1" fillType="Fixed" description="起始行"
                    value="&lt;Dataset&gt;" />
                <CustomSingleRow rowId="2" fillType="HalfFixed" description="模拟开始时间,修改value"
                    value="&lt;XDO name='simstart' kernelType='real' value='2010 1 1' /&gt;" />
                <CustomSingleRow rowId="3" fillType="HalfFixed" description="模拟结束时间,修改value"
                    value="&lt;XDO name='simend' kernelType='real' value='2010 1 5' /&gt;" />
                <CustomSingleRow rowId="4" fillType="HalfFixed" description="中间结果输出时间间隔,修改value"
                    value="&lt;XDO name='histchkp' kernelType='real' value='24' /&gt;" />
                <CustomSingleRow rowId="5" fillType="HalfFixed" description="中间结果输出开始时间,修改value"
                    value="&lt;XDO name='histstart' kernelType='real' value='2010 1 1' /&gt;" />
                <CustomSingleRow rowId="6" fillType="HalfFixed" description="中间结果输出结束时间,修改value"
                    value="&lt;XDO name='histend' kernelType='real' value='2010 1 5' /&gt;" />
                <CustomSingleRow rowId="7" fillType="Fixed" description="结束行"
                    value="/&lt;Dataset&gt;" />
            </CustomClass>
        </Input>
    </Inputs>

</Model>`)






</script>

<style scoped>


</style>
