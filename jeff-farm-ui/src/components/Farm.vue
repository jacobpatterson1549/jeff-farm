<template>
  <div>
    <h2>{{ msg }}</h2>
    <span
      class="tab"
      :class="{ activeTab: selectedTab === tab }"
      v-for="(tab, index) in tabs"
      :key="index"
      @click="selectedTab = tab"
      >{{ tab }}</span
    >

    <div v-show="selectedTab === 'List'">
      <table>
        <tr>
          <th>Name</th>
          <th>Location</th>
          <th>Delete?</th>
        </tr>
        <tr v-for="item in itemList" :key="item.id">
          <td>{{ item.name }}</td>
          <td>{{ item.location }}</td>
          <td>
            <!-- <p>:(</p> -->
            <button v-on:click="deleteItem(item)">Delete</button>
          </td>
        </tr>
      </table>
    </div>

    <div v-show="selectedTab === 'Create'">
      <form @submit="onSubmit">
        <p>
          <label for="farmName">Name</label>
          <input v-model="farmName" required />
        </p>
        <p>
          <label for="farmLocation">Location</label>
          <input v-model="farmLocation" />
        </p>
        <p>
          <input type="submit" value="Submit" />
        </p>
      </form>
    </div>
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
      tabs: ["List", "Create"],
      selectedTab: "List",
      farmName: null,
      farmLocation: null,
      itemList: []
    };
  },
  methods: {
    getTable() {
      return axios.get("farm").then(response => {
        if (response.status === 200) {
          this.itemList = response.data;
        }
        // TODO: else show error
      });
    },
    onSubmit() {
      return axios
        .post("farm", { name: this.farmName, location: this.farmLocation })
        .then(response => {
          if (response.status === 200) {
            return this.getTable().then((this.selectedTab = "List"));
          }
          // TODO: else show error
        });
    },
    deleteItem(farm) {
      if (confirm("Delete " + farm.name + "?")) {
        axios.delete("farm/" + farm.id).then(response => {
          if (response.status === 200) {
            return this.getTable().then((this.selectedTab = "List"));
          }
          // TODO: else show error
        });
      }
    }
  },
  mounted: function() {
    this.getTable();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* table,
th,
td {
  border: 1px solid black;
} */
tr:nth-child(even) {
  background-color: #aaaaaa;
}
.tab {
  background-color: #aaaaaa;
}
.activeTab {
  border: 1px solid black;
}
</style>
