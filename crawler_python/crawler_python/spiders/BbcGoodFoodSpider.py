from scrapy import Item, Field
from scrapy.spiders import CrawlSpider, Rule
from scrapy.linkextractors import LinkExtractor


class BbcGoodFoodCrawler(CrawlSpider):
    name = 'bbcgoodfood'
    allowed_domains = ['www.bbcgoodfood.com']
    start_urls = ['https://www.bbcgoodfood.com/recipes']
    rules = (Rule(LinkExtractor(), callback='parse_item', follow=True),)
    filename = 'bbcgoodfood.txt'

    accept_url = "https://www.bbcgoodfood.com/recipes/"
    reject_urls = ["https://www.bbcgoodfood.com/recipes/category/", "https://www.bbcgoodfood.com/recipes/collection/" ]

    def parse_item(self, response):
        if response.status == 200:
            url = response.url

            item = { }
            item['url'] = response.url
            item['referer'] = response.request.headers.get('Referer')
            item['status'] = response.status

            if url.startswith(self.accept_url):
                matches = True
                for prefix in self.reject_urls:
                    if url.startswith(prefix):
                        matches = False

                if matches:
                    with open(self.filename, 'at') as f:
                        f.write(url +"\n")

            return item