<template>
  <el-row>
    <el-col :span="18">

      <el-form :model="initMessage" ref="createForm" :rules="rules" label-width="280px" v-loading:body="loading">
        <el-form-item label="Message Queue Name:" prop="messageQueueName">
          <el-input v-model="initMessage.messageQueueName" placeholder="Message Queue Name"
                    :disabled="isEdit"></el-input>
        </el-form-item>
        <el-form-item label="Max Message Size(kb):" prop="maxSize">
          <el-input type="number" v-model.number="initMessage.maxSize" placeholder="Max message size"></el-input>
        </el-form-item>
        <el-form-item label="Max pending length:" prop="maxPendingLength">
          <el-input type="number" v-model.number="initMessage.maxPendingLength"
                    placeholder="Max pending length"></el-input>
        </el-form-item>

        <el-form-item label="Send message with password?" prop="publishPassword">
          <el-switch on-text="" off-text="" v-model="initMessage.usePassword"></el-switch>
          <el-input v-if="initMessage.usePassword" type="password" v-model="initMessage.publishPassword"
                    :placeholder="pwdPlaceholder"></el-input>
        </el-form-item>
        <el-form-item label="Owner Team" prop="ownerTeamName">
          <el-input v-model="initMessage.ownerTeamName" placeholder="Owner Team Name"></el-input>
        </el-form-item>
        <el-form-item label="Owner's contact E-mail:" prop="contactEmail">
          <el-input v-model="initMessage.contactEmail" placeholder="Owner's contact email"></el-input>
        </el-form-item>

        <el-form-item label="Tags:" prop="tags">
          <el-input v-model="initMessage.tags" placeholder="Tags, separeted by ','"></el-input>
        </el-form-item>
        <el-form-item label="Summary:" prop="summary">
          <el-input type="textarea" v-model="initMessage.summary" placeholder="Summary"></el-input>
        </el-form-item>
        <el-form-item label="Order Required">
          <el-switch on-text="" off-text="" v-model="initMessage.isOrderRequired"></el-switch>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveMessage">Save</el-button>
        </el-form-item>

      </el-form>

    </el-col>

  </el-row>
</template>
<style>

</style>
<script>
    export default{
        props:{
          initMessage:{
            type:Object,
            default(){
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
            }
          },
          isEdit:{
            type:Boolean,
            default:false
          }
        },
        data(){
            return{
              rules:{
                messageQueueName:[
                  {required:true, message:'Message queue name is required', trigger:'blur'},
                  {min:1, max:50, message:'Length of Message queue name must between 1 to 50 characters', trigger:'blur'}
                ],
                maxSize:[
                  {type:'integer', message:'Max size must be a integer'},
                  {type:'integer', min:1, max:2048, message:'Max size of message must between 1kb to 2048kb', trigger:'blur'}
                ],
                maxPendingLength:[
                  {type:'integer', message:'Max pending count must be a integer'},
                  {type:'integer', max:2000, message:'Max pending count of message must less than 2000', trigger:'blur'}
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
              loading:false,
            }
        },
        methods:{
           saveMessage(){
            this.$refs.createForm.validate((valid) => {
              if(valid){
                this.loading = true;
                this.$http.post(`/api/MessageQueueInfo?isEdit=${this.isEdit}`, this.initMessage).then((response) => {
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
        watch:{
          'initMessage.maxPendingLength':function(newValue, oldValue){
            if(newValue === ''){
                this.initMessage.maxPendingLength = null;
            }
          },
          'initMessage.maxSize':function(newValue, oldValue){
            if(newValue === ''){
                this.initMessage.maxSize = null;
            }
          }
        },
        computed:{
          pwdPlaceholder(){
            return this.isEdit?'Change publish password':'Publish password'
          }
        },
    }




</script>
