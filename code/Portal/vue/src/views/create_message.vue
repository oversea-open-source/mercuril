<template>
  <layout>
    <template slot="content">
      <el-row :gutter="10">
        <el-col :span="14">

          <el-form class="create-form" label-width="240px" v-loading:body="loading">
            <el-form-item label="Message Queue Name:">
              <el-input v-model="form.messageQueueName" placeholder="Message Queue Name"></el-input>
            </el-form-item>
            <el-form-item label="Max Message Size(kb):">
              <el-input type="number" v-model="form.maxSize" placeholder="Max message size"></el-input>
            </el-form-item>
            <el-form-item label="Max pending length:">
              <el-input type="number" v-model="form.maxPendingLength" placeholder="Max pending length"></el-input>
            </el-form-item>

            <el-form-item label="Send message with password?">
              <el-switch on-text="" off-text="" v-model="usePassword"></el-switch>
              <el-input v-if="usePassword" type="password" v-model="form.publishPassword"
                        placeholder="Publish password"></el-input>
            </el-form-item>
            <el-form-item label="Owner Team">
              <el-input v-model="form.ownerTeamName" placeholder="Owner Team Name"></el-input>
            </el-form-item>
            <el-form-item label="Owner's contact E-mail:">
              <el-input v-model="form.contactEmail" placeholder="Owner's contact email"></el-input>
            </el-form-item>

            <el-form-item label="Tags:">
              <el-input v-model="form.tags" placeholder="Tags, separeted by ','"></el-input>
            </el-form-item>
            <el-form-item label="Summary:">
              <el-input type="textarea" v-model="form.summary" placeholder="Summary"></el-input>
            </el-form-item>
            <el-form-item label="Order Required">
              <el-switch on-text="" off-text="" v-model="form.isOrderRequired"></el-switch>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveMessage">Save</el-button>
            </el-form-item>

          </el-form>

        </el-col>

      </el-row>
    </template>
  </layout>
</template>
<style>


</style>
<script>
    export default{
        data(){
            return {
              form:{
                messageQueueName:null,
                maxSize:null,
                maxPendingLength:null,
                publishPassword:null,
                ownerTeamName:null,
                contactEmail:null,
                tags:null,
                summary:null,
                isOrderRequired:false
              },
              usePassword:false,
              loading:false
            }
        },
        methods:{
          saveMessage(){
            this.loading = true;
            this.$http.post('/api/SaveMessageQueueInfo', this.form).then((response) => {
              this.loading = false;
              if(response.body.code === 0){
                this.$message({
                  message:'Message has been saved successfully',
                  type:'success'
                });
              }else{
                this.$message({
                  message:response.body.message,
                  type:'error'
                });
              }

            }, (response) => {
              this.loading = false;
              this.$message({
                message:'API error',
                type:'error'
              })
            });
          }
        }
    }










</script>
