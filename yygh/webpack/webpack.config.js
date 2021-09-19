const path=require("path") //Node.js内置模块
module.exports= {
        entry: './src/main.js', //配置入口文件
        output: {
            path: path.resolve(__dirname, './dist'), //输出路径，__dirname：当前文件所在路径
            filename: 'bundle.js'//输出文件，打包之後的文件
    }
}
