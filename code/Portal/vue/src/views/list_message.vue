<template>
  <layout>
    <template slot="content">
      <div>
        <el-table :data="messageList">
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
      <div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="10"
          layout="sizes, prev, pager, next"
          :total="1000">
        </el-pagination>
      </div>
    </template>
  </layout>
</template>
<style>

</style>
<script>

  export default {
    data(){
      return {
        messageList:[
          {
            messageQueueName:'test',
            ownerTeamName:'ovs',
            contactEmail:'ovs@email.com',
            lastEditUser:'system',
            lastEditDate:'2016-01-01'
          }
        ],
        currentPage:1
      }
    },
    methods: {
     handleSizeChange(val) {
     },
     handleCurrentChange(val) {
       this.currentPage = val;
     }
    },
  }




</script>
