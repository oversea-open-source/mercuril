<template>
  <layout>
    <template slot="content">
      <el-row :gutter="10">
        <el-col :span="18">

          <el-form :model="form" ref="createForm" :rules="rules" label-width="280px" v-loading:body="loading">
            <el-form-item label="Message Queue Name:" prop="messageQueueName">
              <el-input v-model="form.messageQueueName" placeholder="Message Queue Name"></el-input>
            </el-form-item>
            <el-form-item label="Max Message Size(kb):" prop="maxSize">
              <el-input type="number" v-model.number="form.maxSize" placeholder="Max message size"></el-input>
            </el-form-item>
            <el-form-item label="Max pending length:" prop="maxPendingLength">
              <el-input type="number" v-model.number="form.maxPendingLength" placeholder="Max pending length"></el-input>
            </el-form-item>

            <el-form-item label="Send message with password?" prop="publishPassword">
              <el-switch on-text="" off-text="" v-model="usePassword"></el-switch>
              <el-input v-if="usePassword" type="password" v-model="form.publishPassword"
                        placeholder="Publish password"></el-input>
            </el-form-item>
            <el-form-item label="Owner Team" prop="ownerTeamName">
              <el-input v-model="form.ownerTeamName" placeholder="Owner Team Name"></el-input>
            </el-form-item>
            <el-form-item label="Owner's contact E-mail:" prop="contactEmail">
              <el-input v-model="form.contactEmail" placeholder="Owner's contact email"></el-input>
            </el-form-item>

            <el-form-item label="Tags:" prop="tags">
              <el-input v-model="form.tags" placeholder="Tags, separeted by ','"></el-input>
            </el-form-item>
            <el-form-item label="Summary:" prop="summary">
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
              rules:{
                messageQueueName:[
                  {required:true, message:'Message queue name is required', trigger:'blur'},
                  {min:1, max:50, message:'Length of Message queue name must between 1 to 50 characters', trigger:'blur'}
                ],
                maxSize:[
                  {type:'integer', message:'Max size must be a integer'},
                  {min:1, max:2048, message:'Max size of message must between 1kb to 2048kb', trigger:'blur'}
                ],
                maxPendingLength:[
                  {type:'integer', message:'Max pending count must be a integer'},
                  {max:2000, message:'Max pending count of message must less than 2000', trigger:'blur'}
                ],
                publishPassword:[
                  {min:6, max:20, message:'Length of password must between 6 to 20 characters', trigger:'blur'}
                ],
                ownerTeamName:[
                  {required:true, message:'Owner team name is required', trigger:'blur'},
                  {min:1, max:50, message:'Length of owner team name must less than 50 characters', trigger:'blur'}
                ],
                contactEmail:[
                  {type:'email', max:100, message:'Length of contact email must less than 100 characters', trigger:'blur'}
                ],
                tags:[
                  {max:200, message:'Length of tags must less than 200 characters', trigger:'blur'}
                ],
                summary:[
                  {max:500, message:'Length of Message queue name must less than 500 characters', trigger:'blur'}
                ]
              },
              usePassword:false,
              loading:false
            }
        },
        methods:{
          saveMessage(){
            this.$refs.createForm.validate((valid) => {
              console.log('valid:', this.form.maxSize);
              if(valid){
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
                return true;
              }else{
                return false;
              }
            })

          }
        }
    }





























</script>
