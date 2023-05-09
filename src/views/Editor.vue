<template>
  <v-container>

    <!--    数据条目编辑-->
    <v-sheet
      class="my-16"
      min-height="70vh"
      rounded="md"
      style="background-color: #FFDEB4;"
      elevation="4"
    >
      <div class="d-flex align-center">
        <h2 class="ml-4 pt-4">添加模型条目</h2>
        <v-spacer></v-spacer>
        <v-btn
          size="large"
          color="primary"
          prepend-icon="mdi-cloud-upload"
          class="mt-4 mr-8"
          @click="createModelItem()"
        >
          创建模型提交
        </v-btn>
      </div>

      <v-divider class="my-2"></v-divider>

      <v-row justify="center">
        <v-col
          cols="12"
          md="8"
        >
          <v-sheet
            elevation="4"
            min-height="70vh"
            rounded="lg"
            class="ml-4 px-2"
          >
            <h4 class="py-2 pl-2">模型描述文档</h4>
            <CodeMirrorPanel
              v-model=modelInfo.metaXmlStr
              :hintRule=modelHintRule
              height="69vh"
            >
            </CodeMirrorPanel>
          </v-sheet>
        </v-col>

        <v-col
          cols="12"
          md="4"
        >
          <v-sheet
            elevation="4"
            min-height="75vh"
            rounded="lg"
            class="mr-4"
            style="overflow-y: scroll;"
          >
            <h4 class="py-2 pl-2">模型基础信息</h4>

            <v-form class="px-4">
              <VRow>
                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">模型名称：</div>
                  <VTextField
                    v-model="modelInfo.name"
                  />
                </VCol>

                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">关键词：</div>
                  <VTextField
                    v-model="modelInfo.keywords"
                  />
                </VCol>

                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">模型描述：</div>
                  <VTextField
                    v-model="modelInfo.description"
                  />
                </VCol>
              </VRow>
            </v-form>
          </v-sheet>
        </v-col>
      </v-row>
    </v-sheet>

    <!--    数据条目编辑-->
    <v-sheet
      class="my-16"
      min-height="70vh"
      rounded="md"
      style="background-color: #FDF7C3;"
      elevation="4"
    >
      <div class="d-flex align-center">
        <h2 class="ml-4 pt-4">添加数据条目</h2>
        <v-spacer></v-spacer>
        <v-btn
          size="large"
          color="info"
          prepend-icon="mdi-cloud-upload"
          class="mt-4 mr-8"
          @click="createDataItem()"
        >
          创建数据提交
        </v-btn>
      </div>

      <v-divider class="my-2"></v-divider>

      <v-row justify="center">
        <v-col
          cols="12"
          md="8"
        >
          <v-sheet
            elevation="4"
            min-height="70vh"
            rounded="lg"
            class="ml-4 px-2"

          >
            <h4 class="py-2 pl-2">数据描述文档</h4>
            <CodeMirrorPanel
              v-model="dataInfo.metaXmlStr"
              :hintRule=dataHintRule
              height="69vh"
            >
            </CodeMirrorPanel>

            <!--            <div>{{msg}}</div>-->
            <!--            <input v-model="msg"-->
            <!--            />-->
          </v-sheet>

        </v-col>

        <v-col
          cols="12"
          md="4"
        >
          <v-sheet
            elevation="4"
            height="75vh"
            rounded="lg"
            class="mr-4"
            style="overflow-y: scroll;"
          >
            <h4 class="py-2 pl-2">数据基础信息</h4>

            <v-form class="px-4">
              <VRow>
                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">数据是否符合规范：</div>
                  <v-checkbox v-model="dataInfo.processable" label="数据格式是否符合平台要求"></v-checkbox>
                </VCol>

                <VCol cols="12">

                  <div class="text-subtitle-1 text-medium-emphasis">数据类别：</div>
                  <v-select
                    v-model="dataInfo.dataClass"
                    :items="dataClass"
                    item-title="label"
                    item-value="value"
                  ></v-select>
                </VCol>

                <VCol cols="12">
                  <div class="text-subtitle-1

  text-medium-emphasis">数据名称：
                  </div>
                  <VTextField
                    v-model="dataInfo.name"/>
                </VCol>

                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">关键词：</div>
                  <VTextField
                    v-model="dataInfo.keywords"
                  />
                </VCol>
                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">基本描述：</div>
                  <v-textarea
                    v-model="dataInfo.description"
                  />
                </VCol>
                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">数据文件上传：</div>
                  <v-file-input
                    variant="underlined"
                    :show-size=true
                    density="compact"
                    v-model="dataUpFile"
                    prepend-icon="mdi-file-upload"
                    :loading="dataFileLoading"
                    @change="uploadDataFile"
                  >
                  </v-file-input>
                </VCol>

                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">或 远程数据URL：</div>
                  <VTextField
                    v-model="dataInfo.remoteDataUrl"
                  />
                </VCol>
              </VRow>
            </v-form>
          </v-sheet>

        </v-col>
      </v-row>


    </v-sheet>

    <!--脚本部分-->
    <v-sheet
      class="my-16"
      min-height="70vh"
      rounded="md"
      style="background-color: #ceffa9;"
      elevation="4"
    >
      <div class="d-flex align-center">
        <h2 class="ml-4 pt-4">添加数据处理脚本</h2>
        <v-spacer></v-spacer>
        <v-btn
          size="large"
          color="info"
          prepend-icon="mdi-cloud-upload"
          class="mt-4 mr-8"
          @click="createScriptItem()"
        >
          创建脚本提交
        </v-btn>
      </div>

      <v-divider class="my-2"></v-divider>

      <v-row justify="center">

        <v-col
          cols="12"
          md="12"
        >
          <v-sheet
            elevation="4"
            height="75vh"
            rounded="lg"
            class="mx-4 "
            style="overflow-y: scroll;"
          >
            <h4 class="py-2 pl-2">数据基础信息</h4>

            <v-form class="px-4">
              <VRow>

                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">Python环境：</div>
                  <v-radio-group
                    v-model="scriptInfo.pythonVersion"
                    inline
                  >
                    <v-radio
                      label="Python2"
                      value="python2"
                    ></v-radio>
                    <v-radio
                      label="Python3"
                      value="python3"
                    ></v-radio>
                  </v-radio-group>
                </VCol>

                <VCol cols="12">
                  <div class="text-subtitle-1
  text-medium-emphasis">脚本名称：
                  </div>
                  <VTextField
                    v-model="scriptInfo.name"/>
                </VCol>

                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">基本描述：</div>
                  <v-textarea
                    v-model="scriptInfo.description"
                  />
                </VCol>

                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">脚本参数：</div>
                  <v-textarea
                    placeholder="参数一:参数描述;输入类型(FILE或TEXT)&#13;&#10;参数二:参数描述;输入类型(FILE或TEXT)&#13;&#10;..."
                    v-model="argumentsStr"
                  />
                </VCol>

                <VCol cols="12">
                  <div class="text-subtitle-1 text-medium-emphasis">脚本文件上传：</div>
                  <v-file-input
                    variant="underlined"
                    :show-size=true
                    density="compact"
                    v-model="upScriptFile"
                    prepend-icon="mdi-file-upload"
                    @change="uploadScriptFile"
                  >
                  </v-file-input>
                </VCol>
              </VRow>
            </v-form>
          </v-sheet>

        </v-col>
      </v-row>


    </v-sheet>

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
import {ref} from "vue";
import CodeMirrorPanel from "@/components/CodeMirrorPanel";
import requestApi from "@/api/requestApi";

let snackbar = ref(false);
let msg = ref('123');

//模型部分
let modelInfo = ref({
  name: '',
  keywords: '',
  description: '',
  metaXmlStr: `<?xml version="1.0" encoding="UTF-8"?>
<ModelMeta id="xxx">
    <Summary>
        <ModelName>
        </ModelName>
        <Description>
        </Description>
    </Summary>
    <Inputs>
        <Input name="" description="" optional="true" dataClass="">
            <LiteralClass dataType="" description="" />
            <SpecificClass dataType="" description="" suffixName="" />

            <CustomClass dataType="" description="">
                <CustomSingleRow rowId="" name="" dataType="" description="" />

                <CustomMultiRow startRowId="" endRowId="" name="" dataType="" description="" />
            </CustomClass>
        </Input>
        <Input name="" description="" optional="true" dataClass="">
            <LiteralClass dataType="" description="" />

            <SpecificClass dataType="" description="" suffixName="" />

            <CustomClass dataType="" description="">
                <CustomSingleRow rowId="" name="" dataType="" description="" />

                <CustomMultiRow startRowId="" endRowId="" name="" dataType="" description="" />
            </CustomClass>
        </Input>
    </Inputs>
</ModelMeta> `
})

const createModelItem = function () {
  requestApi.createModelItem(modelInfo.value)
    .then(res => {
      if (res.data.code === 0) {
        msg.value = "模型条目创建成功"
        snackbar.value = true;
      }
      console.log(res.data.code)
    })
    .catch(err => {
      console.log(err)
    })
}

const modelHintRule = ref({
  "!top": ["ModelMeta"],
  ModelMeta: {
    attrs: {
      id: ["xxx"]
    },
    children: ["Summary", "Inputs"]
  },

  Summary: {
    children: ["ModelName", "Description"]
  },

  Inputs: {
    children: ["Input"]
  },
  Input: {
    attrs: {name: "", description: "", optional: "", dataClass: ""},
    children: ["LiteralClass", "SpecificClass", "CustomClass"]
  },
  LiteralClass: {
    attrs: {
      dataType: "", description: ""
    }
  },
  SpecificClass: {
    attrs: {
      dataType: "", description: "", suffixName: ""
    }
  },
  CustomClass: {
    attrs: {
      dataType: "", description: ""
    },
    children: ["CustomSingleRow", "CustomMultiRow"]
  },
  CustomSingleRow: {
    attrs: {
      rowId: "", name: "", dataType: "", description: ""
    }
  },
  CustomMultiRow: {
    attrs: {
      startRowId: "", endRowId: "", name: "", dataType: "", description: ""
    }
  }
})


//数据部分
const dataClass = ref([
  {label: "地面高程数据", value: "dem"},
  {label: "土地利用数据", value: "lands"},
  {label: "土壤数据", value: "soil"},
  {label: "植被数据", value: "vegetation"},
  {label: "地质数据", value: "geology"},
  {label: "水文地质数据", value: "hydrology"},
  {label: "气候数据", value: "climate"},
  {label: "其他", value: "others"}
])

let dataInfo = ref({
  processable: false,
  dataClass: 'others',
  name: '',
  keywords: '',
  description: '',
  remoteDataUrl: '',

  fileWebAddress: '',
  realName: '',
  storeName: '',
  fileSize: '',
  metaXmlStr: `<?xml version="1.0" encoding="UTF-8"?>
<DataMeta id="">
    <Identification>
        <Name></Name>
        <Abstract></Abstract>
        <Version></Version>
        <DOI></DOI>
        <CSTR></CSTR>
        <Producer></Producer>
        <PublicDate></PublicDate>
        <UniqueId></UniqueId>
    </Identification>

    <Description>
        <Keywords></Keywords>
        <DetailedDescription></DetailedDescription>
        <TimePeriod>
            <BeginningDateTime></BeginningDateTime>
            <EndingDateTime></EndingDateTime>
        </TimePeriod>
        <SpatialDomain>
            <WestBoundingCoordinate></WestBoundingCoordinate>
            <EastBoundingCoordinate></EastBoundingCoordinate>
            <NorthBoundingCoordinate></NorthBoundingCoordinate>
            <SouthBoundingCoordinate></SouthBoundingCoordinate>
        </SpatialDomain>
        <Status>
            <Process></Process>
            <UpdateFrequency></UpdateFrequency>
        </Status>
        <Source></Source>
        <ProductionProcess></ProductionProcess>
    </Description>

    <DataQuality>
        <AttributeAccuracy>

        </AttributeAccuracy>
        <PositionalAccuracy>

        </PositionalAccuracy>
    </DataQuality>

    <SpatialDataOrganization>
        <SpatialObjectsSystem></SpatialObjectsSystem>
        <VectorObjectInfo>
            <ObjectCount></ObjectCount>
        </VectorObjectInfo>
        <RasterObjectInfo>
            <RowCount></RowCount>
            <ColumnRow></ColumnRow>
            <VerticalCount></VerticalCount>
        </RasterObjectInfo>
        <SpatialReference>
            <CoordinateSystemType></CoordinateSystemType>
            <CoordinateSystemName></CoordinateSystemName>
            <EPSG></EPSG>
        </SpatialReference>
    </SpatialDataOrganization>

    <Content>
        <DataType></DataType>
        <DataFormat></DataFormat>
        <DataForm></DataForm>
        <Attributes>
            <Attribute>
                <AttributeLabel></AttributeLabel>
                <AttributeDefinition></AttributeDefinition>
                <AttributeDomainValues>
                    <EnumeratedDomain></EnumeratedDomain>
                    <RangeDomain></RangeDomain>
                    <CodeSetDomain></CodeSetDomain>
                    <UnrepresentableDomain></UnrepresentableDomain>
                </AttributeDomainValues>
            </Attribute>
        </Attributes>
    </Content>

    <Constraints>
        <CopyRight></CopyRight>
        <AccessConstraints></AccessConstraints>
        <UseConstraints></UseConstraints>
        <Contact>
            <Name></Name>
            <Tel></Tel>
            <Email></Email>
            <Institution></Institution>
            <Address></Address>
        </Contact>
    </Constraints>
</DataMeta>`
})

let dataFileLoading = ref(false)
let dataUpFile = ref([])

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
const dataHintRule = ref({
  DataMeta: {
    attrs: {
      id: ["xxx"]
    },
    children: ["Identification", "Description", "DataQuality", "SpatialDataOrganization", "Content", "Constraints"]
  },
  Identification: {
    children: ["Name", "Abstract", "Version", "Producer", "PublicDate", "UniqueId"]
  },
  Description: {
    children: ["Keywords", "DetailedDescription", "TimePeriod", "SpatialDomain", "Status", "Source", "ProductionProcess"]
  },

  TimePeriod: {
    children: ["BeginningDateTime", "EndingDateTime"]
  },
  SpatialDomain: {
    children: ["WestBoundingCoordinate", "EastBoundingCoordinate", "NorthBoundingCoordinate", "SouthBoundingCoordinate"]
  },
  Status: {
    children: ["Progress", "UpdateFrequency"]
  },
  DataQuality: {
    children: ["AttributeAccuracy", "PositionalAccuracy"]
  },

  SpatialDataOrganization: {
    children: ["SpatialObjectsSystem", "VectorObjectInfo", "RasterObjectInfo", "SpatialReference"]
  },
  VectorObjectInfo: {
    children: ["ObjectCount"]
  },
  RasterObjectInfo: {
    children: ["RowCount", "ColumnRow", "VerticalCount"]
  },
  SpatialReference: {
    children: ["CoordinateSystemType", "CoordinateSystemName", "EPSG"]
  },

  Content: {
    children: ["DataType", "DataFormat", "DataForm", "Attributes"]
  },
  Attributes: {
    children: ["Attribute"]
  },
  Attribute: {
    children: ["AttributeLabel", "AttributeDefinition", "AttributeDomainValues"]
  },
  AttributeDomainValues: {
    children: ["EnumeratedDomain", "RangeDomain", "CodeSetDomain", "UnrepresentableDomain"]
  },

  Constraints: {
    children: ["CopyRight", "AccessConstraints", "UseConstraints", "Contact"]
  }
})

const createDataItem = function () {
  requestApi.createDataItem(dataInfo.value)
    .then(res => {
      if (res.data.code === 0) {
        msg.value = "数据条目创建成功"
        snackbar.value = true;
      }
      console.log(res.data.code)
    })
    .catch(err => {
      console.log(err)
    })
}

const uploadDataFile = (val) => {
  dataInfo.value.storeName = "";
  dataInfo.value.fileSize = "";
  dataInfo.value.realName = "";

  dataFileLoading.value = true
  console.log("upfile:", dataUpFile.value[0])

  let formData = new FormData()
  formData.append("upFile", dataUpFile.value[0])

  requestApi.uploadDataFile(formData)
    .then(res => {
      if (res.data.code === 0) {
        dataInfo.value.fileWebAddress = res.data.data.fileWebAddress;
        dataInfo.value.storeName = res.data.data.storeName;
        dataInfo.value.fileSize = res.data.data.fileSize;
        dataInfo.value.realName = res.data.data.realName;
        msg.value = "文件上传成功"
        snackbar.value = true;
      }
      dataFileLoading.value = false
    })
    .catch(err => {
      console.log(err)
      dataFileLoading.value = false
    })
}


//脚本条目

let scriptInfo = ref({
  name: "",
  description: "",
  arguments: [],
  fileName: "",
  pythonVersion: "python2",
})

let argumentsStr = ref("")


let upScriptFile = ref([])

const uploadScriptFile = (val) => {

  scriptInfo.value.fileName = upScriptFile.value[0].name
  let formData = new FormData()
  formData.append("upScriptFile", upScriptFile.value[0])

  requestApi.uploadScriptFile(formData)
    .then(res => {
      if (res.data.code === 0) {
        msg.value = "文件上传成功"
        snackbar.value = true;
      }
    })
    .catch(err => {
      console.log(err)
      msg.value = "文件上传失败"
      snackbar.value = true;
    })
}


const createScriptItem = () => {
  let argsList = argumentsStr.value.split("\n")
  for (let arg of argsList) {
    if(arg.trim() === "") continue
    scriptInfo.value.arguments.push(
      {
        description: arg.split(";")[0],
        type: arg.split(";")[1],
      }
    )
  }

  // scriptInfo.value.arguments = argumentsStr.value.trim().replace("\n", "").split(";").filter(item => item !== "")

  console.log(scriptInfo.value)
  requestApi.createScriptItem(scriptInfo.value)
    .then(res => {
      if (res.data.code === 0) {
        msg.value = "脚本条目创建成功"
        snackbar.value = true;
      } else {
        msg.value = "脚本条目创建失败"
        snackbar.value = true;
      }
    })
    .catch(err => {
      console.log(err)
    })
}


</script>

<style scoped>

</style>
