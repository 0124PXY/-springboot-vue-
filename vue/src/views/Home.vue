<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 40px">
      <el-col :span="6">
        <el-card style="color: #409eff">
          <div><i class="el-icon-user-solid"/> 用户总数 </div>
          <div style="padding:10px 0; text-align: center; font-weight: bold">
          100W+
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #f56c6c">
          <div><i class="el-icon-money"/> 销售总量 </div>
          <div style="padding:10px 0; text-align: center; font-weight: bold">
            100W+
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67c23a">
          <div><i class="el-icon-bank-card"/> 收益总额 </div>
          <div style="padding:10px 0; text-align: center; font-weight: bold">
            200W+
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #e6a23c">
          <div><i class="el-icon-s-shop"/> 门店总数 </div>
          <div style="padding:10px 0; text-align: center; font-weight: bold">
            40
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px"></div>
      </el-col>

      <el-col :span="12">
        <div id="pie" style="width: 500px; height: 400px"></div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "Home.vue",
  data(){
    return{
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  mounted() {//页面元素渲染之后再触发

    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
      title: {
        text: '各季度用户数量统计',
        subtext: '趋势图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        data: ['第一季度','第二季度','第三季度','第四季度']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name:"月租29的宽带套餐",
          data: [],
          type: 'line'
        },
        {
          name:"月租29的宽带套餐",
          data: [],
          type: 'bar'
        },
        {
          name:"月租49的宽带套餐",
          data: [],
          type: 'line'
        },
        {
          name:"月租49的宽带套餐",
          data: [],
          type: 'bar'
        },
      ]
    };
    var pieOption = {
      title: {
        text: '各季度用户数量统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name:"月租29的宽带套餐",
          type: 'pie',
          radius: '60%',
          center:['25%','55%'],
          label:{              //饼形图上的文本标签
            normal:{
              show:true,
              position:'inner',//标签的位置
              textStyle:{
                fontWeight:300,
                fontSize:16,//文字字体大小
                color:"#fff"
              },
              formatter:'{d}%'
            }
          },
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        },
        {
          name: "月租49的宽带套餐",
          type: 'pie',
          radius: '60%',
          center:['75%','55%'],
          label: {
            normal: {
              show: true,
              position: 'inner',
              textStyle: {
                fontWeight: 300,
                fontSize: 16,
                color: "#fff"
              },
              formatter: '{d}%'
            }
          },
          data: [
            {name: "第一季度", value: 3},
            {name: "第二季度", value: 5},
            {name: "第三季度", value: 2},
            {name: "第四季度", value: 3}
          ],
        }
      ]
    };
    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);


    this.request.get("/echarts/members").then(res =>{
      //填空
      // option.xAxis.data = res.data.x
      option.series[0].data = res.data
      option.series[1].data = res.data
      option.series[2].data = [4,1,2,3]
      option.series[3].data = [5,8,2,6]
          //数据准备完毕后再set
      myChart.setOption(option);

      pieOption.series[0].data = [
        {name:"第一季度",value:res.data[0]},
        {name:"第二季度",value:res.data[1]},
        {name:"第三季度",value:res.data[2]},
        {name:"第四季度",value:res.data[3]},
      ]
      pieChart.setOption(pieOption);
    })

  }
}
</script>

<style scoped>

</style>