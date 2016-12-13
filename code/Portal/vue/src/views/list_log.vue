<template>
  <layout>
    <template slot="content">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item :to="{ path: '/' }">Index</el-breadcrumb-item>
        <el-breadcrumb-item>Message Log</el-breadcrumb-item>
      </el-breadcrumb>
      <div>
        <el-table :data="logList" v-loading:body="loading"  emptyText="There is no available message log">
          <el-table-column
            width="100"
            prop="id"
            label="ID">
          </el-table-column>
          <el-table-column
            width="200"
            prop="messageQueueName"
            label="Message Queue Name">
          </el-table-column>
          <el-table-column
            prop="subscriberApiUrl"
            label="Subscriber API URL">
          </el-table-column>
          <el-table-column
            width="100"
            prop="messageStatus"
            label="Status">
          </el-table-column>
          <el-table-column
            prop="failedReason"
            label="Failed Reason">
          </el-table-column>
          <el-table-column
            prop="formattedLastEditDate"
            label="Last Edit Date">
          </el-table-column>
        </el-table>
      </div>
      <el-row type="flex" class="paginate-row" justify="end">
        <el-col :span="6">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPageNum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="currentPageSize"
            layout="sizes, prev, pager, next"
            :total="totalCount">
          </el-pagination>
        </el-col>
      </el-row>
    </template>
  </layout>
</template>
<style>
  .breadcrumb{
    padding:20px
  }
  .paginate-row{
    padding:10px
  }
</style>
<script>

  export default {
    data(){
      return {
        logList:[],
        currentPageNum:1,
        currentPageSize:10,
        totalCount:0,
        loading:false
      }
    },
    methods: {
     handleSizeChange(val) {
      this.currentPageSize = val;
      this.requestData();
     },
     handleCurrentChange(val) {
       this.currentPageNum = 1
       this.currentPageNum = val;
       this.requestData();
     },
     requestData(){
      this.loading = true;
      this.$http.get(`/api/MessageLog?pageNum=${this.currentPageNum}&pageSize=${this.currentPageSize}`).then((response)=>{
        this.loading = false;
        if(response.body.code === 0){
          this.logList = response.body.data.list;
          this.totalCount=response.body.data.totalCount;
        }else{
          this.$message({
            message:response.body.message,
            type:'error'
          });
        }
      },(response)=>{
          this.$message({
            message:response.body.message,
            type:'error'
          });
      });
     }
    },
    mounted () {
        this.requestData();
    }
  }


</script>
