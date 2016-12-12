<template>
  <layout>
    <template slot="content">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item :to="{ path: '/' }">Index</el-breadcrumb-item>
        <el-breadcrumb-item>Edit Message Queue Info</el-breadcrumb-item>
      </el-breadcrumb>

      <el-row>
        <el-col :span="24">
          <template>
            <el-tabs @tab-click="tabClick" class="message-tab">
              <el-tab-pane label="Edit" name="edit">
                <message-form :initMessage="initMessage" :isEdit="isEdit">
                </message-form>
              </el-tab-pane>
              <el-tab-pane label="Subscriber" name="subscriber">
                <el-row>
                  <el-col :span="18">


                  </el-col>

                </el-row>
              </el-tab-pane>
            </el-tabs>
          </template>
        </el-col>
      </el-row>


    </template>
  </layout>
</template>
<style>
.breadcrumb{
    padding:20px
}
.message-tab{
    width:100%
}





</style>
<script>
    export default{
        data(){
            return {
              initMessage: this.initFormData(),
              isEdit:false
            }
        },
        methods:{
          initFormData(){
            return {
                id:0,
                messageQueueName:null,
                maxSize:null,
                maxPendingLength:null,
                publishPassword:null,
                ownerTeamName:null,
                contactEmail:null,
                tags:null,
                summary:null,
                isOrderRequired:false,
                usePassword:false,
              };
          },
          fetchData(){
            console.log("$route.params", this.$route.params.id);
            if(this.$route.params.id && this.$route.params.id > 0){
              this.isEdit = true;
              this.$http.get(`/api/MessageQueueInfo?id=${this.$route.params.id}`).then((response) => {
                if(response.body.code === 0){
                  this.initMessage = response.body.data.list[0];
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
              this.initMessage = this.initFormData();
            }
          },
          tabClick(tab){
            console.log('tab selected:',tab);
          }
        },
        mounted(){
          this.fetchData();

        },
        watch: {
          '$route':'fetchData'
        },
        components:{
          'message-form':require('../components/message_form.vue')
        }
    }


</script>
