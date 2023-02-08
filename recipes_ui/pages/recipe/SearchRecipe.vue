<template>
  <div>
    <v-container>
      <v-breadcrumbs class="ps-0" :items="items">
        <template v-slot:divider>
          <v-icon>mdi-chevron-right</v-icon>
        </template>
      </v-breadcrumbs>

      <v-row>
        <v-col cols="8" lg="8" md="4" sm="6">
          <v-text-field v-model="values.title" label="Title keywords" hide-details="auto" clearable ></v-text-field>
        </v-col>
         <v-col cols="4" lg="4" md="4" sm="6">
          <v-text-field  v-model="values.writer" label="Author" hide-details="auto" clearable></v-text-field>
        </v-col>
        <v-col cols="6" lg="6" md="4" sm="6">
          <v-text-field v-model="values.ingredients" label="Include ingredients" hide-details="auto" clearable></v-text-field>
        </v-col>
        <v-col cols="6" lg="6" md="4" sm="6">
          <v-text-field v-model="values.blacklisted_ingredients" label="Exclude ingredients" hide-details="auto" clearable></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="4">
          <v-select
            v-model="values.categories"
            :items="options.categories"
            label="Categories"
            item-text="description"
            item-value="id"
            multiple
            clearable
          ></v-select>
        </v-col>

        <v-col cols="4">
          <v-select
            v-model="values.difficulty"
            :items="options.difficulty"
            item-text="description"
            item-value="id"
            label="Difficulty"
          ></v-select>
        </v-col>

        <v-col cols="4">
          <v-select
            v-model="values.servings"
            :items="options.servings"
            item-text="description"
            item-value="id"
            label="Servings"
            prepend-icon="mdi-food-apple"
          ></v-select>
        </v-col>

        <v-col cols="4">
          <v-select
            v-model="values.numberOfIngredients"
            :items="options.numberOfIngredients"
            item-text="description"
            item-value="id"
            label="Number of ingredients"
          ></v-select>
        </v-col>

        <v-col cols="4">
          <v-text-field
            v-model="values.maxCalories"
            label="Max calories"
            type="number"
            suffix="kcal"
            clearable
          ></v-text-field>
        </v-col>

        <v-col cols="4">
          <v-text-field
            v-model="values.maxTimeRequired"
            label="Time required"
            type="number"
            suffix="minutes"
            clearable
          ></v-text-field>
        </v-col>

      </v-row>

      <v-row>
        <div class="d-flex justify-space-between flex-wrap flex-sm-nowrap">
          <v-btn color="primary" class="mb-3" @click="searchForData">Search</v-btn>
        </div>
      </v-row>


      <v-row v-if="errors.length == 0 && results.length > 0">
        <v-col cols="12">
          <h5 class="text-18">Search results</h5>
        </v-col>
        <v-col
          cols="12"
          xl="3"
          lg="3"
          md="4"
          sm="6"
          v-for="(item, index) in results"
          :key="index"
        >
          <RecipeCard :item="item" />
        </v-col>

        <v-col cols="12" class="mt-6">
          <v-divider></v-divider>
        </v-col>
      </v-row>

      <v-row>
        <p v-if="errors.length">
          <b>Please correct the following error(s):</b>
          <ul>
            <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
          </ul>
        </p>
      </v-row>
    </v-container>
    <Location />
    <Footer />
  </div>
</template>
<script>
export default {
  head: {
    title: "Search Resturant"
  },
  data: () => ({
    errors: [],
    items: [
      {
        text: "Home",
        disabled: false,
        href: "/"
      },
      {
        text: "Recipes",
        disabled: false,
        href: "/"
      },
      {
        text: "Search",
        disabled: true,
        href: "/"
      }
    ],
    checkbox: true,
    radioGroup: 1,
    value: [20, 40],

    options: {
      difficulty: [
      {
        id:"any",
        description:"Any"
      },
      {
        id:"easy",
        description:"Easy"
      },
      {
        id:"medium",
        description:"Medium"
      },
      {
        id:"Hard",
        description:"hard"
      }],
      servings:  [
      {
        id:"any",
        description:"Any"
      },
      {
        id:"1",
        description:"1"
      },
      {
        id:"2",
        description:"2"
      },
      {
        id:"3",
        description:"3"
      },
      {
        id:"4",
        description:"4"
      },
      {
        id:"100",
        description:"More"
      }],
      numberOfIngredients: [
      {
        id:"any",
        description:"Any"
      },
      {
        id:"1",
        description:"1"
      },
      {
        id:"2",
        description:"2"
      },
      {
        id:"3",
        description:"3"
      },
      {
        id:"4",
        description:"4"
      },
      {
        id:"5",
        description:"5"
      },
      {
        id:"6",
        description:"6"
      },
      {
        id:"7",
        description:"7"
      },
      {
        id:"8",
        description:"8"
      },
      {
        id:"9",
        description:"9"
      },
      {
        id:"10",
        description:"10"
      },
      {
        id:"100",
        description:"More"
      }],
      categories: []
    },
    values: {
      difficulty: "any",
      servings: "any",
      numberOfIngredients: "any",
      categories: [],
      maxTimeRequired: "",
      maxCalories: "",
      title: "",
      ingredients: "",
      blacklisted_ingredients: "",
      writer: "",
    },
    results: []
  }),
  created() {
    console.log("created called");
    this.fetchCategories();
  },
  methods: {
    async fetchCategories() {
       let response = await this.$axios.$get('category/all');
       this.options.categories = response;
       console.log(this.options.categories);
    },
    async searchForData(){
      if (!this.checkForm()) {
        return;
      }
      let params ={
        title: this.values.title
      };

      if (this.values.writer != "") {
        params.writer = this.values.writer;
      }

      if (this.values.difficulty != "any") {
        params.difficulty = this.values.difficulty;
      }

      if (this.values.maxCalories) {
        params.calories = this.values.maxCalories;
      }

      if (this.values.servings != "any") {
        params.servings = this.values.servings;
      }

      if (this.values.numberOfIngredients != "any") {
        params.totalIngredients = this.values.numberOfIngredients;
      }

      if (this.values.maxTimeRequired) {
        params.time = this.values.maxTimeRequired;
      }

      if (this.values.ingredients != "") {
        params.ingredients =this.values.ingredients.split(",");
      }

       if (this.values.blacklisted_ingredients != "") {
        params.exclude_ingredients =this.values.blacklisted_ingredients.split(",");
      }


      let response = await this.$axios.$post('recipe/search', params);
      console.log(response);

      this.results = response.recipes;
    },
    checkForm: function () {
      let ok = true;

      this.errors = [];

      if (!this.values.title) {
        ok = false;
        this.errors.push('Title required.');
      }

      if (!ok) {
        return false;
      } else {
        return true;
      }
    }
  },
};
</script>
