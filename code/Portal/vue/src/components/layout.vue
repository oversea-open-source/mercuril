<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-menu theme="dark" default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                 router="router">
          <el-submenu index="1">
            <template slot="title">Message</template>
            <el-menu-item index="/">List</el-menu-item>
            <el-menu-item index="/message/create">Create</el-menu-item>
          </el-submenu>
          <el-menu-item index="/messagelog">Log</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
    <div class="grid-content">
      <slot name="content">
      </slot>
    </div>
  </div>
</template>
<style>
    body{
      margin:0px;
    }

    .grid-content {
        padding:24px;
        height:auto;
        max-width:1024px;
        margin: 0 auto;
        position: relative;
    }

    .el-menu-demo {
        padding-left: 55px;
    }



</style>
<script>
    export default {
        data(){
          return {
            router:true
          }
        },

        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }





</script>
