<template>
  <div>
    <el-row type="flex" justify="end">
      <el-col :span="3">
        <el-button type="primary" @click="addSubscribe">Subscribe</el-button>
      </el-col>
    </el-row>
    <el-row class="subscriber-table">
      <el-col :span="24">
        <el-table :data="subscriberList" v-loading:body="loading">
          <el-table-column
            prop="id"
            label="ID">
          </el-table-column>
          <el-table-column
            prop="subscriberApiUrl"
            label="Subscriber API URL">
          </el-table-column>
          <el-table-column
            prop="retryCount"
            label="Retry Count">
          </el-table-column>
          <el-table-column
            prop="failedNotifyEmail"
            label="Failed Notify Email">
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
      </el-col>
    </el-row>
  </div>
</template>
<style>
  .paginate-row{
    padding:10px
  }
  .subscriber-table{
    padding:15px 0px 0px 0px
  }


</style>
<script>
    export default{
        data(){
            return{
                subscriberList:[],
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
              this.$http.get(`/api/Subscriber?pageNum=${this.currentPageNum}&pageSize=${this.currentPageSize}&messageQueueId=${this.$route.params.id}`).then((response)=>{
                this.loading = false;
                if(response.body.code === 0){
                  this.subscriberList = response.body.data.list;
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
           },
           addSubscribe(){
            this.$router.push({path:`/subscriber/create/${this.$route.params.id}`});
           }
        },
        mounted () {
          this.requestData();
        }
     }


</script>
