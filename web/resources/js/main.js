"use strict";

Vue.component('Navi', {
    data() {
        return {
            activeIndex: '1',
        }
    },
    props: ['message', 'backUrl'],
    template: 
        `<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="1">处理中心</el-menu-item>
            <el-submenu index="2">
                <template slot="title">我的工作台</template>
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
                <el-submenu index="2-4">
                    <template slot="title">选项4</template>
                    <el-menu-item index="2-4-1">选项1</el-menu-item>
                    <el-menu-item index="2-4-2">选项2</el-menu-item>
                    <el-menu-item index="2-4-3">选项3</el-menu-item>
                </el-submenu>
            </el-submenu>
            <el-menu-item index="3" disabled>消息中心</el-menu-item>
            <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
        </el-menu>`,
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        }
    }
})

Vue.component('FootContent', {
    template: 
        `<span>刘哥太强了</span>`,
})

new Vue({
    el: '#nav',
});

new Vue({
    el: 'footer' 
});