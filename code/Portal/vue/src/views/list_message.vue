<template>
  <layout>
    <template slot="content">
      <div>
        <el-table :data="messageList" v-loading:body="loading">
          <el-table-column
            prop="messageQueueName"
            label="Message Queue Name">
          </el-table-column>
          <el-table-column
            prop="ownerTeamName"
            label="Owner Team Name">
          </el-table-column>
          <el-table-column
            prop="contactEmail"
            label="Contact Email">
          </el-table-column>
          <el-table-column
            prop="lastEditUser"
            label="Edit User">
          </el-table-column>
          <el-table-column
            prop="lastEditDate"
            label="Edit Date">
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
  .paginate-row{
    padding:10px
  }
</style>
<script>

  export default {
    data(){
      return {
        messageList:[],
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
       this.currentPageSize = 1
       this.currentPageNum = val;
       this.requestData();
     },
     requestData(){
      this.loading = true;
      this.$http.get(`/api/MessageList?pageNum=${this.currentPageNum}&pageSize=${this.currentPageSize}`).then((response)=>{
        this.loading = false;
        if(response.body.code === 0){
          this.messageList = response.body.data.list;
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
        console.log('mounted');
        this.requestData();
    }
  }

















</script>
