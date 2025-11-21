<template>
  <div style="height: 100vh; width: 1400px;">
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入套餐名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入宽带带宽" suffix-icon="el-icon-message" class="ml-5" v-model="bandwidth"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>


    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="name" label="套餐名称" width="140"></el-table-column>
      <el-table-column prop="fee" label="套餐资费" width="140"></el-table-column>
      <el-table-column prop="type" label="宽带类型">
      </el-table-column>
      <el-table-column prop="router" label="路由器设备" width="120"></el-table-column>
      <el-table-column prop="bandwidth" label="宽带带宽（M）"></el-table-column>

      <el-table-column label="操作"  width="500" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleOrder(scope.row)">故障报修 <i class="el-icon-edit"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>


    <el-dialog title="故障信息" :visible.sync="orderDialogVis" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="预约时间">
          <el-date-picker v-model="form.time" type="datetime" placeholder="选择预约时间" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="套餐名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="套餐价格">
          <el-input v-model="form.fee" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="orderDialogVis = false">取 消</el-button>
        <el-button type="primary" @click="saveOrder">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>


export default {
  name: "Band",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      bandwidth: "",
      form: {
        fee:""
      },
      dialogFormVisible: false,
      orderDialogVis: false,
      records:[]
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/band/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          bandwidth: this.bandwidth,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    saveOrder() {
      this.request.post("/error", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.orderDialogVis = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },

    handleOrder(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.orderDialogVis = true  // 打开订单信息对话框
    },

    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    reset() {
      this.name = ""
      this.bandwidth = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
  },

}
</script>


