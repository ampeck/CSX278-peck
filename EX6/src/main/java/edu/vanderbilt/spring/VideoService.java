package edu.vanderbilt.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoService {

	private VideoRepository videos_;
	
	@Autowired
	public VideoService(VideoRepository repo) {
		videos_ = repo;
	}
	
	@RequestMapping(value="/video/{id}", method=RequestMethod.POST)
	public Video getVideo(@PathVariable("id") Long videoId, @RequestBody Video video){
		
		System.out.println("id:"+videoId);
		Video curVid = videos_.findOne(videoId);
		
		if ((video != null) && (curVid != null)) {
		if (video.getName() != null) videos_.findOne(videoId).setName(video.getName());
		if (video.getSize() != null) videos_.findOne(videoId).setSize(video.getSize());
		if (video.getGenre() != null) videos_.findOne(videoId).setGenre(video.getGenre());
		if (video.getUrl() != null) videos_.findOne(videoId).setUrl(video.getUrl());
		}
		
		return videos_.findOne(videoId);
	}
	
	@RequestMapping(value="/video", method=RequestMethod.POST)
	public Iterable<Video> addVideo(@RequestBody Video video){
		videos_.save(video);
		return videos_.findAll();
	}
	
	@RequestMapping(value="/test/foo", method=RequestMethod.GET)
	public String myTest() {
		return "Test";
	}
	
	@RequestMapping(value="/video", method=RequestMethod.GET)
	public Iterable<Video> getVideoList(){
		return videos_.findAll();
	}
	
	@RequestMapping(value="/video/{id}", method=RequestMethod.DELETE)
	public void deleteVideo(@PathVariable("id") Long videoId){
		videos_.delete(videoId);
	}
	
	
}
