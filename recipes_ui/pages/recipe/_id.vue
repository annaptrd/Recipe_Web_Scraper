<template>
  <div>
      <v-container>
        <v-breadcrumbs class="ps-0" :items="items">
          <template v-slot:divider>
            <v-icon>mdi-chevron-right</v-icon>
          </template>
        </v-breadcrumbs>
        <!-- gallery  -->
        <v-row dense v-if="item && item.difficulty && item.nutritionInfo">
            <v-col cols="12" xl="6" lg="6">
                <v-img v-if="item.photo_url" width="100%" height="100%" :src="item.photo_url"></v-img>
                <v-img v-if=" !item.photo_url" width="100%" height="100%" :src="require(`~/assets/images/food/default.jpg`)"></v-img>
            </v-col>
            <v-col cols="12" class="mt-4">
                <div class="d-flex justify-space-between flex-wrap align-center mb-3">
                    <h1 class="me-2">{{ item.title }}</h1>
                    <div class="mb-3">
                        <span class="text-14 me-1">  {{ item.difficulty.description }} </span>
                        <span
                            v-for="(star, index) in 1"
                            :key="index"
                        >
                            <v-icon small color="warning">mdi-star</v-icon>
                        </span>
                        <span class="text-14 me-1"> {{ item.totalTime }} </span>
                    </div>
                </div>
                <div class="grey--text text--darken-1 align-middle text-14 mb-4 d-flex align-center flex-wrap">
                   <v-icon left small color="grey">mdi-earth</v-icon>
                   <span class="primary--text me-2"> {{ item.writer }}</span>
                </div>
                <div class="grey--text text--darken-1 align-middle text-14 mb-4 d-flex align-center flex-wrap">
                    <v-icon left small color="grey">mdi-clock-outline</v-icon>
                    Preparation: {{ item.preparationTime }}, Cooking: {{ item.cookingTime }}
                </div>
                <div class="grey--text text--darken-1 align-middle text-14 mb-4 d-flex align-center flex-wrap">
                    <v-icon left small color="grey">mdi-food-fork-drink</v-icon>
                   {{ item.servings }}
                </div>
                <p class="mb-5 text-14">{{ item.description }}</p>
            </v-col>
        </v-row>
        <!-- end::gallery  -->

        <div>
          <v-tabs
            v-model="tab"
            class="mb-8"
          >
                <v-tab class="text-capitalize" href="#tab-1">How to make</v-tab>
                <v-tab class="text-capitalize" href="#tab-2">Nurtitional info</v-tab>
                <v-tab class="text-capitalize" href="#tab-3">Comments</v-tab>
            </v-tabs>

            <v-tabs-items v-model="tab">
                <v-tab-item
                    value="tab-1"
                >
                    <v-row>
                        <v-col cols="12" xl="12">
                            <Box>
                                <div slot="boxSidebar">

                                    <div v-for="(g, index) in item.ingredientGroups" :key="index" class=" d-flex flex-column ">
                                        <h3>{{ g.description}}</h3>
                                        <span v-for="(i,index2) in g.igredients" :key="index2" class="mb-2 food-menu-link">{{i.description}}</span>
                                    </div>
                                </div>
                                <div slot="boxContent">
                                    <div class="px-8">
                                        <v-row>
                                            <v-col cols="12">
                                                <h3 class="fw-bold">Steps</h3>
                                            </v-col>
                                            <v-col cols="12" v-for="(s, index) in item.steps" :key="index">
                                                <div class="d-flex justify-space-between align-center flex-wrap">
                                                    <div class="d-flex flex-wrap">
                                                        <div>
                                                            <v-chip class="text-uppercase mb-2" text-color="secondary" small color="grey lighten-4">Step {{ index+1 }}</v-chip>
                                                            <p class="text-14 grey--text text--darken-1">{{ s.description }}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </v-col>
                                        </v-row>
                                    </div>
                                </div>
                            </Box>
                        </v-col>
                    </v-row>
                </v-tab-item>
                <v-tab-item value="tab-2" >
                    <v-row>
                        <v-col cols="12" lg="6" v-if="item.nutritionInfo">
                            <v-row >
                                <v-col cols="12" xl="6" lg="6" md="6" sm="6" class="mt-2">
                                    Calories: {{ item.nutritionInfo.calories}}
                                </v-col>
                            </v-row>
                             <v-row >
                                <v-col cols="12" xl="6" lg="6" md="6" sm="6" class="mt-2">
                                    Carbohydrates: {{ item.nutritionInfo.carbohydrates}}
                                </v-col>
                            </v-row>
                            <v-row >
                                <v-col cols="12" xl="6" lg="6" md="6" sm="6" class="mt-2">
                                       Protein: {{ item.nutritionInfo.protein}}
                                </v-col>
                            </v-row>
                            <v-row >
                                <v-col cols="12" xl="6" lg="6" md="6" sm="6" class="mt-2">
                                    Fat: {{ item.nutritionInfo.fat}}
                                </v-col>
                            </v-row>
                            <v-row >
                                <v-col cols="12" xl="6" lg="6" md="6" sm="6" class="mt-2">
                                    Saturated fat: {{ item.nutritionInfo.saturated_fat}}
                                </v-col>
                            </v-row>
                            <v-row >
                                <v-col cols="12" xl="6" lg="6" md="6" sm="6" class="mt-2">
                                    Sugars: {{ item.nutritionInfo.sugars}}
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                </v-tab-item>
                <v-tab-item
                    value="tab-3"
                >
                   <v-row>
                       <v-col cols="12" lg="6">
                           <!-- user-post  -->
                            <div class="mb-6">
                                <div class="d-flex align-center flex-wrap mb-4">
                                    <v-avatar size="48" class="me-4">
                                        <img src="~/assets/images/faces/8.png" alt="">
                                    </v-avatar>
                                    <div>
                                        <h5 class="mb-0">Comments</h5>
                                    </div>
                                </div>
                                <div class="d-flex align-center mb-2">
                                    {{ item.comments}}
                                </div>
                            </div>
                       </v-col>
                   </v-row>
                </v-tab-item>
            </v-tabs-items>
        </div>



      </v-container>
      <Location />
      <Footer />

  </div>
</template>
<script>
  export default {
    head: {
        title: 'Food Menu'
    },
    data: () => ({
      items: [
        {
          text: 'Home',
          disabled: false,
          href: '/',
        },
        {
          text: 'Recipes',
          disabled: false,
          href: '/',
        },
        {
          text: 'Resturants',
          disabled: true,
          href: '/',
        },
      ],
      formSelectItems: ['Foo', 'Bar', 'Fizz', 'Buzz'],
      mobileItems: [
          'Order Online', 'Book a Table', 'Reviews',
       ],
       text: 'hello',
      tab: null,
      checkbox: true,
      radioGroup: 1,
      value: [20, 40],
      item: {}
    }),
    created() {
      let id = this.$route.params.id;
      console.log("loading recipe: " + id);
      this.fetchRecipe(id);
    },
    methods: {
      async fetchRecipe(id) {
        let response = await this.$axios.$get('recipe/' + id);
        this.results = response;
        console.log(this.results);

        this.items = [
          {
            text: 'Home',
            disabled: false,
            href: '/',
          },
          {
            text: 'Recipes',
            disabled: false,
            href: '/',
          },
          {
            text: this.results.category.description,
            disabled: false,
            href: '/',
          },
          {
            text: this.results.title,
            disabled: true,
            href: '/',
          },
        ];
        this.item = this.results;
      }
    }
  }
</script>
<style lang="scss">
.food-menu-link {
    font-size: 14px;
    text-decoration: none;
    color: #4B566B !important;
    font-weight: 400;
    &:hover {
        color: #000 !important;
        border-right: 3px solid #D23F57;
    }
    &.active {
        color: #000 !important;
        border-right: 3px solid #D23F57;

    }
}
</style>

