from scrapy import Item, Field
from scrapy.spiders import CrawlSpider, Rule
from scrapy.linkextractors import LinkExtractor

class CookingClassyCrawler(CrawlSpider):
    name = 'cookingclassy'
    allowed_domains = ['www.cookingclassy.com']
    start_urls = ['https://www.cookingclassy.com/recipes']
    rules = (Rule(LinkExtractor(), callback='parse_item', follow=True),)
    filename = 'cookingclassy.txt'

    accept_url = "https://www.cookingclassy.com/"
    reject_urls = ["https://www.cookingclassy.com/tag/", "https://www.cookingclassy.com/recipes/", "https://www.cookingclassy.com/wprm_print" , "https://www.cookingclassy.com/page/", "https://www.cookingclassy.com/about/", "https://www.cookingclassy.com/newsletter/", "https://www.cookingclassy.com/contact/", "https://www.cookingclassy.com/wp-login.php?", "https://www.cookingclassy.com/wp-login.php"]

    def parse_item(self, response):
        if response.status == 200:
            url = response.url

            item = { }
            item['url'] = response.url
            item['referer'] = response.request.headers.get('Referer')
            item['status'] = response.status

            if url.startswith(self.accept_url) and not "comment" in url:
                matches = True
                for prefix in self.reject_urls:
                    if url.startswith(prefix):
                        matches = False

                if matches:
                    with open(self.filename, 'at') as f:
                        f.write(url +"\n")

            return item