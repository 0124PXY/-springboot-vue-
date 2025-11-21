<template>
    <el-container style="min-height: 100vh">
      <el-aside width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
        <Aside :is-collapse="isCollapse" :logo-text-show="LogoTextShow"/>
      </el-aside>

      <el-container>
        <el-header style="border-bottom: 1px solid #ccc;">
          <Header :collapseBtnClass="collapseBtnClass" :collapse="isCollapse" :user="user"/>
        </el-header>
        <el-main>
<!--          表示当前页面的子路由会在<router-view/>里面展示-->
          <router-view @refreshUser="getUser"/>
        </el-main>
      </el-container>
    </el-container>
</template>

<script>
// @ is an alias to /src

import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'HomeView',

  data() {
    return {
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false ,//默认是展开的
      sideWidth:200,
      LogoTextShow:true,
      user:{},
    }
  },
  components:{
    Aside,
    Header
  },
  created() {
    //从后台获取最新的User数据
    this.getUser()
  },
  methods:{
    collapse() {   //点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){//收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.LogoTextShow = false
      } else{    //展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.LogoTextShow = true
      }
    },
    //写一个函数获取用户最新的数据
    getUser() {
      let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username:""
      //从后台获取User数据,头像更新
      this.request.get("/user/username/" + username).then(res =>{
        //重新赋值后台的最新User数据
        this.user = res.data
      })
    },
  }
}
</script>

