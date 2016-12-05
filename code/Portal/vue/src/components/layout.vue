<template>
    <div>
        <el-row>
            <el-col :span="24">
                <el-menu theme="dark" default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                    <el-menu-item index="1">配置</el-menu-item>
                    <el-menu-item index="2">历史消息查询</el-menu-item>
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
    .grid-content {
        padding:24px;
        height:auto;
        max-width:1024px;
        margin: 0 auto;
        position: relative;
    }
</style>
<script>
    export default {
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>
