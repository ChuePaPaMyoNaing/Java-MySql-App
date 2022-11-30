package jp.co.cybermissions.mysqlapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SiteController
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/sites")
public class SiteController {

    @Autowired
    private SiteRepository siteRepository;

    @GetMapping(path="")
    public Iterable<Site> getSites() {
        return siteRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Site getSite(@PathVariable(value = "id") Integer id) {
        return siteRepository.findById(id).orElse(null);
    }

    @PostMapping(path = "")
    public Site createSite(@RequestBody Site site) {
        Site s = new Site();
        s.setName(site.getName());
        s.setUrl(site.getUrl());
        return siteRepository.save(s);
    }

    @PutMapping(path = "/{id}")
    public Site updateSite(@PathVariable(value = "id")Integer id, @RequestBody Site site) {
        Site s = siteRepository.findById(id).orElse(null);
        if (s != null) 
            return null;
        s.setName(site.getName());
        s.setUrl(site.getUrl());
        return siteRepository.save(s);
        
    }

    @DeleteMapping(path = "/{id}")
    public void deleteSite(@PathVariable(value = "id")Integer id) {
        siteRepository.deleteById(id);
    }
}