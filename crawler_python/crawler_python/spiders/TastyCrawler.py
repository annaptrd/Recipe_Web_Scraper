from scrapy import Item, Field
from scrapy.spiders import CrawlSpider, Rule
from scrapy.linkextractors import LinkExtractor


class TastyCrawler(CrawlSpider):
    name = 'tasty'
    allowed_domains = ['tasty.co']
    start_urls = ['https://tasty.co']
    rules = (Rule(LinkExtractor(), callback='parse_item', follow=True),)
    filename = 'tasty.txt'

    accept_url = "https://tasty.co/recipe/"
    reject_urls = ["printview", "page"]

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