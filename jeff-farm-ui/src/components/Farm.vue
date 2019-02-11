<template>
  <div>
    <h1>{{ msg }}</h1>
    <p>ittemList:</p>
    <p>{{ itemList }}</p>
    <table>
      <tr>
        <th>Name</th>
        <th>Location</th>
      </tr>
      <tr v-for="item in itemList" :key="item.id">
        <td>{{ item.name }}</td>
        <td>{{ item.location }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Farm",
  props: {
    msg: String
  },
  data() {
    return {
      itemList: []
    };
  },
  methods: {
    getTable() {
      axios.get("farm").then(response => {
        console.log(response);
        this.itemList = response;
      });
    }
  },
  mounted: function() {
    this.getTable();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1 {
  margin: 40px 0 0;
}
</style>
