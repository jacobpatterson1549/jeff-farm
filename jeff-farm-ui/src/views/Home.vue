<template>
  <div>
    <img alt="Vue logo" src="../assets/logo.png" />
    <Farm msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
// @ is an alias to /src
import Farm from "@/components/Farm.vue";

export default {
  name: "farm",
  components: {
    Farm
  }
};
</script>
