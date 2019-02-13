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
          <th>Update</th>
          <th>Delete?</th>
        </tr>
        <tr v-for="item in itemList" :key="item.id">
          <td>{{ item.name }}</td>
          <td>{{ item.location }}</td>
          <td>
            <button v-on:click="openUpdateTab(item)">Update</button>
          </td>
          <td>
            <button v-on:click="deleteItem(item)">Delete</button>
          </td>
        </tr>
      </table>
    </div>

    <!-- TODO: This is similar to the delete form.  It would be nice to share code -->
    <div v-show="selectedTab === 'Update'">
      <form @submit.prevent="updateItem">
        <p>
          <label for="farmName">Name</label>
          <input name="farmName" v-model="farm.name" required />
        </p>
        <p>
          <label for="farmLocation">Location</label>
          <input name="farmLocation" v-model="farm.location" />
        </p>
        <p>
          <button v-on:click="selectedTab = 'List'">Cancel</button>
          <input type="submit" value="Update" />
        </p>
      </form>
    </div>

    <div v-show="selectedTab === 'Create'">
      <form @submit.prevent="postItem">
        <p>
          <label for="farmName">Name</label>
          <input name="farmName" v-model="farm.name" required />
        </p>
        <p>
          <label for="farmLocation">Location</label>
          <input name="farmLocation" v-model="farm.location" />
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
      farm: {
        id: -1,
        name: null,
        location: null
      },
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
    postItem() {
      this.farm.id = -1;
      return axios.post("farm", this.farm).then(response => {
        if (response.status === 200) {
          return this.getTable().then((this.selectedTab = "List"));
        }
        // TODO: else show error
      });
    },
    openUpdateTab(farm) {
      this.selectedTab = "Update";
      this.farm = farm;
    },
    updateItem() {
      axios.put("farm", this.farm).then(response => {
        if (response.status === 200) {
          // TODO: Maybe have confirmation that update is successful?
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
