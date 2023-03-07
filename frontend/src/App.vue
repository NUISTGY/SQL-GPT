<template>
  <div class="container">
    <el-container>
      <el-header>
        <span class="header-title">🧠 SQL-GPT</span>
      </el-header>
        <el-main style="display: flex; height: 100%; align-items: stretch;">
          <el-input v-model="textarea" ref="textInput" @keyup.enter.native="submitInput" type="textarea" :rows="32" :value="textarea" style="flex: 1; margin-right: 10px;"></el-input>
          <el-input type="textarea" :rows="32" v-loading="loading" :value="responsearea" readonly style="flex: 1; margin-left: 10px;"></el-input>
        </el-main>
      <el-footer>
        <span class="footer-title">Powered by OpenAI gpt-3.5-turbo</span>
      </el-footer>
    </el-container>
    <div>
      <el-dialog
        title="Anything else?"
        :visible.sync="dialogVisible"
        width="30%">
        <el-input
          placeholder="Give more requirements ~"
          v-model="comment"
          clearable>
        </el-input>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel...</el-button>
          <el-button type="primary" @click="dialogVisible = false; submitData()">OK !</el-button>
        </span>
      </el-dialog>
  </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      textarea: '',
      responsearea:"",
      comment: '',
      loading: false,
      dialogVisible: false
    }
  },
  methods: {
    submitInput(event) {
      if (!event.shiftKey) {
        event.preventDefault(); // 阻止默认的换行符操作
        console.log(this.textarea); // 处理回车键操作
        this.dialogVisible = true;
        this.$refs.textInput.blur();
      }
    },
    async submitData() {
      const data = JSON.stringify({
        ddl: this.textarea,
        comment: this.comment
      });
      this.loading = true;
      const resp = await this.$axios.post('/api/getmockdata', data).then((resp) => {
        return resp
      });
      this.loading = false;
      //去除chatContent的首尾单引号
      resp.data.data.chatContent = resp.data.data.chatContent.substring(1, resp.data.data.chatContent.length - 1);
      this.responsearea = resp.data.data.chatContent;
      console.log(resp.data.data.chatContent);
    }
  }
}
</script>

<style lang="less">
 .el-header {
    background-color: #FFFFFF;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
    color: #333;
    line-height: 60px;
    border-radius: 10px 10px 0 0;
    z-index: 100;
    position: fixed;
    width: 100%;
  }

  .el-footer {
    background-color: #FFFFFF;
    box-shadow: 0 -0.5rem 1rem rgba(0, 0, 0, 0.15);
    color: #333;
    line-height: 60px;
    border-radius: 0 0 10px 10px;
    z-index: 100;
    position: fixed;
    bottom: 0;
    width: 100%;
  }

  .el-main {
    background-color: #F7F8FA;
    color: #333;
    height: calc(100vh - 120px);
    margin-top: 60px;
    padding: 20px;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
    position: relative;
    z-index: 1;
  }

  .header-title {
    font-size: 26px;
    display: flex;
    align-items: center; /*垂直居中*/
    font-weight: bold; 
    margin-left: 20px;
  }

  .footer-title {
    font-size: 16px;
    display: flex;
    justify-content: center; /*水平居中*/
    align-items: center; /*垂直居中*/
    margin-left: 20px;
  }
</style>