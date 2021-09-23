import axios from '../axios'

/* 
 * 用户管理模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/user/save',
        method: 'post',
        data
    })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/user/delete',
        method: 'post',
        data
    })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/user/findPage',
        method: 'post',
        data
    })
}
// 导出Excel用户信息
export const exportUserExcelFile = (data) => {
    return axios({
        url: '/user/exportUserExcelFile',
        method: 'post',
        data
    })
}
// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
    return axios({
        url: '/user/findPermissions',
        method: 'get',
        params
    })
}
// 根据用户名查找
export const findByName = (params) => {
    return axios({
        url: '/user/findByName',
        method: 'get',
        params
    })
}
// 更新用户密码
export const updatePassword = (params) => {
    return axios({
        url: '/user/updatePassword',
        method: 'post',
        params
    })
}

// 查詢
export const getPageList = (current, limit, searchObj) => {
    return axios({
        url: `http://localhost:8201/admin/hosp/hospitalSet/findPage/${current}/${limit}`,
        method: 'post',
        data: searchObj
    })
}


//刪除醫院
export const deleteHospSet = (id) => {
   return axios({
        url: `http://localhost:8201/admin/hosp/hospitalSet/${id}`,
        method: 'delete'
   })
}

//批量刪除刪除醫院
export const batchHostSet = (idList) => {
    return axios({
         url: `http://localhost:8201/admin/hosp/hospitalSet/batchRemoveHospitalSet`,
         method: 'delete',
        data:idList
    })
 }


 //锁定和取消锁定
 //批量刪除刪除醫院
export const lockHospSet = (id,status) => {
    return axios({
         url: `http://localhost:8201/admin/hosp/hospitalSet/lockHospitalSet/${id}/${status}`,
         method: 'put'
    })
 }
