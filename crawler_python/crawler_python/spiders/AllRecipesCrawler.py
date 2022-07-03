from scrapy import Item, Field
from scrapy.spiders import CrawlSpider, Rule
from scrapy.linkextractors import LinkExtractor


class AllRecipesCrawler(CrawlSpider):
    name = 'allrecipes'
    allowed_domains = ['www.allrecipes.com']
    start_urls = ['https://www.allrecipes.com']
    rules = (Rule(LinkExtractor(), callback='parse_item', follow=True),)
    filename = 'allrecipes.txt'

    accept_url = "https://www.allrecipes.com/recipe/"
    reject_urls = ["printview", "page"]

    def parse_item(self, response):
        if response.status == 200:
            url =  response.url

            item = { }
            item['url'] = response.url
            item['referer'] = response.request.headers.get('Referer')
            item['status'] = response.status

            if url.startswith(self.accept_url):
                matches = True
                for prefix in self.reject_urls:
                    if prefix in url:
                        matches = False

                if matches:
                    with open(self.filename, 'at') as f:
                        f.write(url +"\n")

            return item
