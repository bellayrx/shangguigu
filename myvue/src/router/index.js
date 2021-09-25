import Vue from 'vue'
import Router from 'vue-router'

import Ma from "../views/Ma"
import Ma from "../views/Mas"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Ma
    },
    {
      path: '/sb',
      component: Mas
    }
  ]
})
