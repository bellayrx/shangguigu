import request from '@/utils/request'

export default {
 deleteHospSet (id) {
  return request({
       url: `http://localhost:8201/admin/hosp/hospitalSet/${id}`,
       method: 'delete'
  })
}
}