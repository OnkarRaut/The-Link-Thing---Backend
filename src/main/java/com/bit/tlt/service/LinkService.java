package com.bit.tlt.service;

import com.bit.tlt.repository.LinkRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkService {

    private LinkRepository linkRepository;
    private UserService userService;
    private Mapper dozerMapper;

    @Autowired
    public LinkService(LinkRepository linkRepository, UserService userService, Mapper dozerMapper) {
        this.linkRepository = linkRepository;
        this.userService = userService;
        this.dozerMapper = dozerMapper;
    }

    public List<com.bit.tlt.model.Link> getLinks() {
        return this.mapLinks(this.linkRepository.findAll());
    }

    public List<com.bit.tlt.model.Link> getLinksByUserId(Long id) {
        List<Link> links = this.linkRepository.findAllByCreatedBy(id);
        return this.mapLinks(links);
    }

    public List<com.bit.tlt.model.Link> getLinksByUsername(String username) {
        return this.getLinksByUserId(this.userService.findUserIdByUsername(username));
    }

    private List<com.bit.tlt.model.Link> mapLinks(List<Link> links) {
        return links
                .stream()
                .map(o -> this.dozerMapper.map(o, com.bit.tlt.model.Link.class))
                .collect(Collectors.toList());
    }

    public com.bit.tlt.model.Link saveLink(com.bit.tlt.model.Link link, String username) {
        return this.saveLink(link, this.userService.findUserIdByUsername(username));
    }

    public com.bit.tlt.model.Link saveLink(com.bit.tlt.model.Link link, Long createUserId) {
        Link linkEntity = this.dozerMapper.map(link, Link.class);
        linkEntity.setCreatedBy(createUserId);
        Link savedLink = this.linkRepository.save(linkEntity);
        return this.dozerMapper.map(savedLink, com.bit.tlt.model.Link.class);
    }

    public void deleteLink(Long linkId) {
        this.linkRepository.deleteById(linkId);
    }

}
