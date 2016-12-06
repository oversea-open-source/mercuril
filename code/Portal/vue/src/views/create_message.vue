<template>
  <layout>
    <template slot="content">
      <el-row :gutter="10">
        <el-col :span="14">

          <el-form class="create-form" label-width="240px" v-loading:body="loading">
            <el-form-item label="Message Queue Name:">
              <el-input v-model="name" placeholder="Message Queue Name"></el-input>
            </el-form-item>
            <el-form-item label="Max Message Size(kb):">
              <el-input v-model="maxSize" placeholder="Max message size"></el-input>
            </el-form-item>
            <el-form-item label="Max pending length:">
              <el-input v-model="maxPendingLength" placeholder="Max pending length"></el-input>
            </el-form-item>

            <el-form-item label="Send message with password?">
              <el-switch on-text="" off-text="" v-model="usePassword"></el-switch>
            </el-form-item>
            <el-form-item label="Owner Team">
              <el-input v-model="ownerTeamName" placeholder="Owner Team Name"></el-input>
            </el-form-item>
            <el-form-item label="Owner's contact E-mail:">
              <el-input v-model="contactEmail" placeholder="Owner's contact email"></el-input>
            </el-form-item>

            <el-form-item label="Tags:">
              <el-input v-model="tags" placeholder="Tags, separeted by ','"></el-input>
            </el-form-item>
            <el-form-item label="Summary:">
              <el-input type="textarea" v-model="summary" placeholder="Summary"></el-input>
            </el-form-item>
            <el-form-item label="Order Required">
              <el-switch on-text="" off-text="" v-model="isOrderRequired"></el-switch>
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
              name:'',
              maxSize:'',
              maxPendingLength:'',
              usePassword:false,
              ownerTeamName:'',
              contactEmail:'',
              tags:'',
              summary:'',
              isOrderRequired:false,
              loading:false
            }
        },
        methods:{
          saveMessage(){
            this.loading = true;
            this.$http.post('/api/SaveMessageQueueInfo').then((response) => {
              this.loading = false;
            }, (response) => {
              this.loading = false;
            });
          }
        }
    }


</script>
