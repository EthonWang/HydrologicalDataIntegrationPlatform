<template>

  <v-container class="v-container--fluid py-6 px-6 bg-grey-lighten-3" >
    <v-row>
      <v-col cols="3">
        <v-sheet rounded="lg" >
          <v-list rounded="lg" height="80vh">
            <v-list-item
              v-for="(layer,index) in layersTableData"
              :key="layer.id"
              link
            >
              <v-list-item-title @click="onChangeLayer(layer)" class="mt-3 overflow-auto">
               图层 {{index+1}} : {{ layer.name }}
              </v-list-item-title>
              <v-divider class="my-2"></v-divider>

            </v-list-item>

          </v-list>
        </v-sheet>
      </v-col>

      <v-col>
        <v-sheet
          min-height="80vh"
          rounded="lg"
        >
          <div id="map" style="width: 100%;height: 80vh"></div>
          <!--  -->
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>


</template>

<script setup>
import {onMounted, ref} from "vue";
import mapboxgl from "mapbox-gl";
import 'mapbox-gl/dist/mapbox-gl.css'

const onChangeLayer=(layer)=>{
  console.log(layer.id)
  if(currentLayerId.value!=layer.id){
    if(currentLayerId.value!="-1") {
      map.removeLayer(currentLayerId.value)
      map.removeSource(currentLayerId.value)
    }
    map.addSource(layer.id, {
      type: "raster",
      tiles: [layer.sourceUrl],
      tileSize: 256, // 切片的最小展示尺寸（可选，单位：像素，默认值为 512，即 1024/2）
    });

    map.addLayer({
      id: layer.id,
      source: layer.id,
      type:"raster",
    });
    currentLayerId.value=layer.id
  }
}

const currentLayerId=ref("-1");

let layersTableData=ref([
  {
    id: '1',
    name:"南京路网",
    sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3Amap_lines&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
  },{
    id: '2',
    name:"江苏省DEM30",
    sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3Ajiangsu_merged&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
  },{
    id: '3',
    name:"国界",
    sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3A国家矢量&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
  },{
    id: '4',
    name:"2023年省级行政区划",
    sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3A2023年省级行政区划&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
  },{
    id: '5',
    name:"2023年地级行政区划",
    sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3A2023年地级行政区划&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
  },{
    id: '6',
    name:"2023年县级行政区划",
    sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3A2023年县级行政区划&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
  },{
    id: '7',
    name:"九段线",
    sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3A九段线&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
  },{
    id: '8',
    name:"南京市DEM30",
    sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3A南京市DEM30&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
  },{
    id: '9',
    name:"长江三角洲DEM30",
    sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3ALC08_L1TP_118038_20220518_20220525_02_T1_B5&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
  },

])



var map=ref(null)

const initMap=()=>{
  mapboxgl.accessToken = 'pk.eyJ1IjoiY2FydG9kYmluYyIsImEiOiJja202bHN2OXMwcGYzMnFrbmNkMzVwMG5rIn0.Zb3J4JTdJS-oYNXlR3nvnQ';
  map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/streets-v12', // style URL
    center: [119, 32], // starting position [lng, lat]
    zoom: 9, // starting zoom
    projection: 'mercator'
  });
  map.on('load', () => {

    // let layerInfo={
    //   id: '2',
    //   name:"长三角区域dem",
    //   sourceUrl:"http://localhost:8061/geoserver/geo-data-hub/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&FORMAT=image%2FPNG&TRANSPARENT=true&STYLES&LAYERS=geo-data-hub2%3Ajiangsu_merged&exceptions=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A3857&WIDTH=512&HEIGHT=512&BBOX={bbox-epsg-3857}",
    // }
    //
    // map.addSource(layerInfo.id, {
    //   type: "raster",
    //   tiles: [layerInfo.sourceUrl],
    //   tileSize: 256, // 切片的最小展示尺寸（可选，单位：像素，默认值为 512，即 1024/2）
    // });
    //
    //
    // let newLayer = {
    //   id: layerInfo.id,
    //   source: layerInfo.id,
    //   type:"raster",
    // };
    // map.addLayer(newLayer);
  })

}

onMounted(() => {
  console.log("init map")
  initMap()
})


const layerStyle=ref( {
  line: {
    layout: {
      visibility: "visible",
      // "line-sort-key":999
    },
    paint: {
      "line-color": "#" + Math.random().toString(16).substr(2, 6),
        "line-blur": 0,
        "line-dasharray": [1, 0],
        // "line-gradient":"",  //ignore  Requires source to be "geojson".
        "line-opacity": 1,
        // "line-pattern": "",  //ignore  Optional resolvedImage.
        "line-width": 1,
    },
  },
  circle: {
    layout: {
      visibility: "visible", //One of "visible", "none"
      // "circle-sort-key":999,
    },
    paint: {
      "circle-blur": 0,
        "circle-color": "#000000",
        "circle-opacity": 1,
        "circle-pitch-alignment": "viewport", //One of "map", "viewport"
        "circle-pitch-scale": "map", //One of "map", "viewport"
        "circle-radius": 5,
        "circle-stroke-color": "#000000",
        "circle-stroke-opacity": 1,
        "circle-stroke-width": 0,
        "circle-translate": [0, 0],
        "circle-translate-anchor": "map", // One of "map", "viewport"
    },
  },
  fill: {
    layout: {
      visibility: "visible", //One of "visible", "none"
      // "fill-sort-key":999
    },
    paint: {
      "fill-antialias": true,
        "fill-color": "#" + Math.random().toString(16).substr(2, 6),
        "fill-outline-color": "#000000",
        "fill-opacity": 1,
    },
  },
})


const handleLayerShowChange=(row)=> {
  console.log(row)
  if (row.show) {
    this.handleLayoutChange(row.id, "visibility", "visible");
  } else {
    this.handleLayoutChange(row.id, "visibility", "none");
  }
}

const handleRemoveSource=(sourceName)=>{
  map.removeSource(sourceName);
}

const handleRemoveLayer=(layerName)=> {
  map.removeLayer(layerName);
}

const handleLayoutChange=(layerName, key, value)=> {
  console.log("layout:", layerName, key, value);
  map.setLayoutProperty(layerName, key, value);
}

const handlePaintChange=(layerName, key, value)=> {
  console.log("paint:", layerName, key, value);
  map.setPaintProperty(layerName, key, value);
}

</script>

<style scoped>

</style>
