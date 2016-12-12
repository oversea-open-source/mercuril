<template>
  <layout>
    <template slot="content">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item :to="{ path: '/' }">Index</el-breadcrumb-item>
        <el-breadcrumb-item>Edit Message Queue Info</el-breadcrumb-item>
        <el-breadcrumb-item>Add Subscriber</el-breadcrumb-item>
      </el-breadcrumb>

      <el-row>
        <el-col :span="18">

          <el-form :model="initSubscribe" ref="addSubscribeForm" :rules="rules" label-width="280px"
                   v-loading:body="loading">
            <el-form-item label="Subscriber's API URL:" prop="subscriberApiUrl">
              <el-input v-model="initSubscribe.subscriberApiUrl" placeholder="Subscriber's API URL"
                        :disabled="isEdit"></el-input>
            </el-form-item>
            <el-form-item label="Retry Count:" prop="retryCount">
              <el-input type="number" v-model.number="initSubscribe.retryCount" placeholder="Retry Count"></el-input>
            </el-form-item>
            <el-form-item label="Auto Replay?" prop="isAutoReplay">
              <el-switch on-text="" off-text="" v-model="initSubscribe.isAutoReplay"></el-switch>
              <el-input v-if="initSubscribe.isAutoReplay" type="number" v-model="initSubscribe.autoReplayInterval"
                        placeholder="Interval before next replay"></el-input>
            </el-form-item>
            <el-form-item label="Failed Notify Email:" prop="failedNotifyEmail">
              <el-input v-model="initSubscribe.failedNotifyEmail" placeholder="Failed Notify Email"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveSubscriber">Save</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </template>
  </layout>
</template>
<style>
.breadcrumb{
    padding:20px
}




</style>
<script>
    export default{
        data(){
            return {
              initSubscribe: this.initFormData(),
              isEdit:false,
              loading:false,
              rules:{
                subscriberApiUrl:[
                  {required:true, message:'Subscriber\'s API URL is required', trigger:'blur'},
                  {min:1, max:500, message:'Length of Subscriber\'s API URL must between 1 to 500 characters', trigger:'blur'}
                ],
                retryCount:[
                  {type:'integer', message:'Retry count must be a integer'},
                  {type:'integer', min:0, max:20, message:'Retry count must between 0 to 20', trigger:'blur'}
                ],
                autoReplayInterval:[
                  {type:'integer', message:'Auto replay interval must be a integer'},
                  {type:'integer', min:5, max:60000, message:'Auto replay interval must between 5 to 648000', trigger:'blur'}
                ],
                failedNotifyEmail:[
                  {type:'email', message:'Failed notify email must be a valid email address'},
                  {type:'email', max:100, message:'Length of failed notify email must less than 100 characters', trigger:'blur'}
                ]
              },
            }
        },
        methods:{
          initFormData(){
            return {
                id:0,
                messageQueueId:parseInt(this.$route.params.messageQueueId, 10),
                subscriberApiUrl:null,
                retryCount:null,
                isAutoReplay:false,
                autoReplayInterval:null,
                failedNotifyEmail:null
              };
          },
          fetchData(){
            console.log("$route.params.id", this.$route.params.id);
            if(this.$route.params.id && this.$route.params.id > 0){
              this.isEdit = true;
              this.$http.get(`/api/Subscriber?id=${this.$route.params.id}`).then((response) => {
                if(response.body.code === 0){
                  this.initSubscribe = response.body.data.list[0];
                }else{
                  this.$message({
                    type:'error',
                    message: response.body.message
                  });
                }
              }, () => {
                  this.$message({
                    type:'error',
                    message:'API error'
                  });
              });
            }else{
              this.isEdit = false;
              this.initSubscribe = this.initFormData();
            }
          },
          saveSubscriber(){
            console.log("this.initSubscribe.messageQueueId:", this.initSubscribe.messageQueueId);
            this.$refs.addSubscribeForm.validate((valid) => {
              if(valid){
                this.loading = true;
                this.$http.post(`/api/Subscriber?isEdit=${this.isEdit}`, this.initSubscribe).then((response) => {
                  this.loading = false;
                  if(response.body.code === 0){
                    this.$message({
                      message:'Subscriber has been saved successfully',
                      type:'success'
                    });
                  }else{
                    this.$message({
                      message:response.body.message,
                      type:'error'
                    });
                  }

                }, () => {
                  this.loading = false;
                  this.$message({
                    message:'API error',
                    type:'error'
                  })
                });
                return true;
              }else{
                return false;
              }
            })

          }
        },
        mounted(){
          this.fetchData();

        },
        watch: {
          '$route':'fetchData'
        }
    }









</script>
