<template>
  <v-container class="v-container--fluid py-6 px-6">
    <v-row>
      <v-col
        cols="12"
        sm="3"
        class="pa-1"
      >
        <v-sheet
          rounded=""
          min-height="90vh"
        >
          <div class="d-flex justify-space-between px-1 pt-1">
            <h2>模型列表</h2>
            <v-btn prepend-icon="mdi-file-document-plus-outline" color="warning" to="Editor">
              添加模型
            </v-btn>
          </div>
          <div style="height: 83vh;overflow-y: scroll;padding-left: 5px;padding-right: 5px;">
            <v-card
              v-for="item in modelList"
              style="margin-top: 5px;"
              elevation="2"
            >
              <v-card-title style="padding-bottom: 5px">
                <span style="font-size: 22px;line-height: 1rem;font-weight: 600;">{{ item.name }}</span>
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
                    color="success"
                    variant="outlined"
                    style="font-size: 10px;"
                  >
                    {{ k }}
                  </v-chip>
                </div>
              </v-card-subtitle>

              <v-card-text style="padding-top: 5px;padding-bottom: 5px">
                <span style="font-size: 15px">{{ item.description }}</span>
              </v-card-text>

              <v-divider></v-divider>

              <v-card-actions style="padding-top: 6px;padding-bottom: 6px">
                <v-spacer></v-spacer>
                <v-btn prepend-icon="mdi-magnify"
                       color="primary"
                       rounded="xl"
                       elevation="2"
                       variant="outlined"
                       size="small"
                       @click="seeModelMeta(item.metaXmlStr)"
                >
                  查看模型描述文档
                </v-btn>
                <v-btn prepend-icon="mdi-delete-outline"
                       color="primary"
                       rounded="xl"
                       elevation="2"
                       variant="outlined"
                       size="small"
                       @click="modelSelected=item"
                >
                  选择
                </v-btn>
                <!--                    <v-btn size="small" color="warning"  variant="outlined" icon="mdi-file-search"></v-btn>-->
              </v-card-actions>
            </v-card>
          </div>
        </v-sheet>
      </v-col>

      <!--     中间-->
      <v-col
        cols="12"
        sm="6"
        class="pa-1"
      >
        <v-sheet
          v-if="modelSelected==null"
          min-height="90vh"
          style="padding-left: 10px;padding-right: 10px;"
        >
          请先选择模型
        </v-sheet>

        <v-sheet
          v-else
          min-height="90vh"
          rounded=""
          style="padding-left: 10px;padding-right: 10px;"
        >

          <v-card elevation="0" style="margin-top: 5px">
            <v-card-item>
              <v-card-title>{{ modelSelected.name }}</v-card-title>

              <v-card-subtitle>
                <div style="overflow: hidden;
                            text-overflow: ellipsis;
                            white-space: nowrap;">
                  <v-chip
                    v-for="k in modelSelected.keywords.split(',')"
                    :key="k"
                    :value="k"
                    size="x-small"
                    class="mr-1"
                    color="success"
                    variant="outlined"
                    style="font-size: 10px;"
                  >
                    {{ k }}
                  </v-chip>
                </div>
              </v-card-subtitle>
            </v-card-item>
          </v-card>

          <v-divider :thickness="1" style="padding-bottom: 5px"></v-divider>

          <div style="height: 75vh;overflow-y: scroll">
            <v-card
              v-for="item in modelSelected.metaXmlJson.Inputs.Input"
              elevation="2" style="margin-top: 5px">
              <v-card-item>
                <v-card-title>
                  <div class="d-flex">
                    <span>{{ item.name }}</span>

                    <v-chip
                      style="margin-top: 6px;margin-left: 10px;"
                      color="warning"
                      label
                      size="small"
                      text-color="white"
                    >
                      <v-icon start icon="mdi-label" size="small"></v-icon>
                      {{ item.dataClass }}
                    </v-chip>

                    <v-spacer></v-spacer>
                    <v-chip v-if="item.optional=='false'" size="small" color="primary">必填</v-chip>
                    <v-chip v-if="item.optional=='true'" size="small" color="secondary">可选</v-chip>
                  </div>
                </v-card-title>

                <v-card-subtitle>{{ item.description }}</v-card-subtitle>
              </v-card-item>

              <v-card-text>
                {{ item.dataClass }}
              </v-card-text>
            </v-card>

          </div>


        </v-sheet>
      </v-col>

      <!--      数据-->
      <v-col
        cols="12"
        sm="3"
        class="pa-1"
      >
        <v-sheet
          rounded=""
          min-height="90vh"
        >
          <div class="d-flex justify-space-between px-1 pt-1">
            <h2>数据列表</h2>
            <v-btn prepend-icon="mdi-file-document-plus-outline" color="primary" to="Editor">
              添加数据
            </v-btn>
          </div>
          <v-divider></v-divider>
          <div style="height: 83vh;overflow-y: scroll;padding-left: 5px;padding-right: 5px;">
            <v-card
              class="mx-auto"
              v-for="item in dataList"
              :key="`${item.id}`"
              elevation="2"
              style="margin-top: 5px;"
            >
              <v-card-item style="padding-bottom: 0;">
                <div class="text-sm-body-1 mb-1 d-flex justify-md-space-between">
                  <div style="display: flex;align-items: center; height:20px;">
                    <div style="overflow: hidden;
                      text-overflow: ellipsis;
                      -webkit-box-orient: vertical;
                      display: -webkit-box;
                      -webkit-line-clamp: 1;"
                    >
                      <span style="font-size: 16px;line-height: 1rem;font-weight: 600;">{{ item.name }}</span>
                    </div>
                  </div>
                  <v-btn
                    v-if="item.remoteDataUrl===''"
                    height="20"
                    variant="text"
                    width="20"
                    size="small"
                    icon="mdi-download-outline"
                    @click="downloadData(item.fileWebAddress)"
                  ></v-btn>
                  <v-btn
                    v-else
                    height="20"
                    variant="text"
                    width="20"
                    size="small"
                    icon="mdi-share-variant-outline"
                    @click="downloadData(item.fileWebAddress,item.remoteDataUrl)"
                  ></v-btn>
                </div>

              </v-card-item>

              <v-card-text style="padding-bottom: 1px;">

                <div style="overflow: hidden;
                            text-overflow: ellipsis;
                            white-space: nowrap;
                            padding-bottom: 1px"
                >
                  <span style="color: coral;  font-size: 10px; margin-right: 5px">{{
                      dataClassJson[item.dataClass]
                    }}</span>

                  <v-chip
                    v-for="k in item.keywords.split(',')"
                    :key="k"
                    :value="k"
                    size="x-small"
                    class="mr-1"
                    color="success"
                    variant="outlined"
                    style="font-size: 10px;"
                  >
                    {{ k }}
                  </v-chip>
                </div>

                <span style="
                          font-size: 10px;
                          overflow: hidden;
                          text-overflow: ellipsis;
                          -webkit-box-orient: vertical;
                          display: -webkit-box;
                          -webkit-line-clamp: 2;"
                >{{ item.description }}
              </span>
              </v-card-text>

              <v-divider></v-divider>

              <v-card-actions class="mt-2" style="padding-bottom: 5px">
                <v-spacer></v-spacer>
                <v-btn prepend-icon="mdi-magnify"
                       color="primary"
                       rounded="xl"
                       elevation="2"
                       variant="outlined"
                       @click="seeDataMeta(item.metaXmlStr)"
                       size="x-small"
                >
                  查看数据描述文档
                </v-btn>
              </v-card-actions>
            </v-card>

          </div>

        </v-sheet>
      </v-col>
    </v-row>

  </v-container>
</template>

<script setup>

import requestApi from "@/api/requestApi";
import {onMounted, ref} from "vue";

onMounted(() => {
  getModelList()
  getDataList()
})

let modelList = ref([])
let modelSelected = ref(null)

const getModelList = function () {
  requestApi.getModelList()
    .then(res => {
      if (res.data.code === 0) {
        modelList.value = res.data.data
        modelSelected.value = res.data.data[0]
      }
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
}

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
})
let dataList = ref([])
let dataMetaDialog = ref(false)

let curMetaXmlStr = ref("")

let dataDeleteDialog = ref(false)
let dataId = ref("");

function getDataList() {
  requestApi.getDataListNoPage()
    .then(res => {
      if (res.data.code === 0) {
        dataList.value = res.data.data
      }
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
}

const seeDataMeta = function (val) {
  curMetaXmlStr.value = val
  dataMetaDialog.value = true
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


</script>
