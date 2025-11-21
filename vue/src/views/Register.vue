<template>
  <div class="wrapper">
    <div style="margin: 100px auto; background-color: #fff;width: 350px;height: 400px;padding: 20px;border-radius: 10px"><!--padding内边距 圆角-->
      <div style="margin: 20px 0; text-align: center;font-size: 24px"><b>注 册</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input placeholder="请输入密码" size="medium" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input><!--margin上下空了10间距，v-model绑定属性-->
          </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" size="medium" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input placeholder="请确认密码" size="medium" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.confirmPassword"></el-input>
        </el-form-item>
        <div style="margin: 5px 0; text-align: center ">
          <el-button type="primary" size="small" autocomplete="off" @click="login">注册</el-button>
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/login')">返回登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      user:{},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  methods:{
    login(){
      this.$refs['userForm'].validate((valid) => {
        if (valid) {//表单校验合法
          if (this.user.password !== this.user.confirmPassword){
            this.$message.error("两次输入的密码不一致")
            return false
          }
          this.request.post("/user/register",this.user).then(res =>{
            if(res.code === '200'){
              this.$message.success("注册成功")
            }else{
              this.$message.error(res.msg)//错误提示
            }
          })
        }
      });
    }
  }
}
</script>

<style>
   .wrapper{
     height:100vh;/* 100vh表示百分之百的高度，铺满整个屏幕*/
     background-image: linear-gradient(to bottom right,#FC466B,#3F5EFB);/*左上角到右下角渐变色*/
     overflow:hidden;/* 超出部分隐藏掉*/
   }
</style>