import axios from 'axios'
// import Vue from "vue";

const instance = axios.create({
  // baseURL:Vue.prototype.reqUrl,
  baseURL: "http://localhost:8001",
  timeout: 100000,
});


export default {




  requestTest() {
    return instance.get("/hello")
  },

  register(data) {
    return instance.post("/user/register", data)
  },

  login(data) {
    return instance.post("/user/login", data)
  },


  getDataList(data) {
    return instance.post("/getDataList", data)
  },

  getDataListProcess(){
    return instance.get("/getDataListProcess")
  },

  createDataItem(data) {
    return instance.post("/createDataItem", data)
  },

  deleteDataItem(dataId) {
    return instance.get("/deleteDataItem/"+dataId)
  },

  uploadDataFile(data) {
    return instance.post("/uploadFile", data)
  },


  getModelList() {
    return instance.get("/getModelList")
  },

  createModelItem(data) {
    return instance.post("/createModelItem", data)
  },

  deleteModelItem(dataId) {
    return instance.get("/deleteModelItem/"+dataId)
  },

  createScriptItem(data) {
    return instance.post("/addScriptItem",data)
  },

  uploadScriptFile(data) {
    return instance.post("/uploadScriptFile",data)
  },

  getScriptList(){
    return instance.get("/getScriptList")
  },

  execScript(data,seeId){
    return instance.post("/execScript/"+seeId,data)
  },

  createUserData(data){
    return instance.post("/createUserData",data)
  },

  getUserDataList(userId){
    return instance.get("/getUserDataList/"+userId)
  },

  deleteUserData(dataId){
    return instance.get("/deleteUserData/"+dataId)
  },


  getScriptStatusEvent(seeId){
    return "http://localhost:8001/createSeeConnect/"+seeId
  },

  closeSeeConnect(seeId){
    return instance.get("/closeSeeConnect/"+seeId)
  },

  getScriptTaskList(userId){
    return instance.get("/getScriptTaskList/"+userId)
  }



}
