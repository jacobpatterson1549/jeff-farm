<template>
  <div>
    <img alt="Vue logo" src="../assets/chickens.jpg" />
    <h1>jeff-farm-ws</h1>
    <Farm msg="viewing farms" />
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
