<template>
  <v-container class="v-container--fluid py-8 px-6">
    <v-row>
      <v-col
        cols="12"
        sm="3"
        class="pa-1"
      >
        <v-sheet
          rounded=""
          min-height="80vh"
        >
          <div class="d-flex justify-space-between px-1 pt-1">
            <h2>数据列表</h2>
            <v-btn prepend-icon="mdi-file-document-plus-outline" color="primary" to="Editor">
              添加数据
            </v-btn>
          </div>
        </v-sheet>
      </v-col>

      <v-col
        cols="12"
        sm="6"
        class="pa-1"
      >
        <v-sheet
          min-height="80vh"
          rounded=""
        >
          interaction
        </v-sheet>
      </v-col>

      <v-col
        cols="12"
        sm="3"
        class="pa-1"
      >
        <v-sheet
          rounded=""
          min-height="80vh"
        >
          <div class="d-flex justify-space-between px-1 pt-1">
            <h2>模型列表</h2>
            <v-btn prepend-icon="mdi-file-document-plus-outline" color="warning" to="Editor">
              添加模型
            </v-btn>
          </div>
        </v-sheet>
      </v-col>
    </v-row>

  </v-container>


</template>

<script setup>
import {ref} from 'vue'
import CodeMirrorPanel from "@/components/CodeMirrorPanel";



</script>
<style>

</style>
