package com.dev4.sunbbang.bakery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev4.sunbbang.model.AuthVO;
import com.dev4.sunbbang.model.BakeryVO;
import com.dev4.sunbbang.model.FoodVO;
import com.dev4.sunbbang.model.MemberVO;
import com.dev4.sunbbang.model.PageVO;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Async
@RestController
public class BakeryController {

	@Autowired
	BakeryService bakeryService;

	@Autowired
	Gson gson;

	@RequestMapping("/bakery/joinBakery")
	public boolean joinBakery( MemberVO memberVO, BakeryVO bakeryVO, MultipartFile image, String imageName) {
		try {
			log.info("memberVO :: {}", memberVO);
			log.info("bakeryVO :: {}", bakeryVO);
			log.info("image :: {}", image);
			log.info("imageName :: {}", imageName);

			bakeryService.joinBakery(memberVO, bakeryVO, image);
			return true;
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return false;
		}
	}

	@RequestMapping("/bakery/myShop")
	public Object myShop(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.myShop(bakeryVO));
	}

	@RequestMapping("/bakery/changeBakery")
	public boolean changeBakery(@RequestBody BakeryVO bakeryVO, @RequestBody MultipartFile image) {
		try {
			bakeryService.myShop(bakeryVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/menuList")
	public Object menuList(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.menuList(bakeryVO));
	}

	@RequestMapping("/bakery/addMenu")
	public boolean addMenu(@RequestBody FoodVO foodVO) {
		try {
			bakeryService.addMenu(foodVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/modifyMenu")
	public boolean modifyMenu(@RequestBody FoodVO foodVO) {
		try {
			bakeryService.modifyMenu(foodVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/deleteMenu")
	public boolean deleteMenu(@RequestBody FoodVO foodVO) {
		try {
			bakeryService.deleteMenu(foodVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/boardToggle")
	public Object boardToggle(@RequestBody BakeryVO bakeryVO) {
		return gson.toJson(bakeryService.boardToggle(bakeryVO));
	}

	@RequestMapping("/bakery/searchBakery")
	public Object searchBakery(@RequestBody PageVO pageVO) {
		return gson.toJson(bakeryService.searchBakery(pageVO));
	}

	@RequestMapping("/bakery/setFollow")
	public boolean setFollow(@RequestBody AuthVO authVO, @RequestBody BakeryVO bakeryVO) {
		try {
			bakeryService.setFollow(authVO, bakeryVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/setAlarm")
	public boolean setAlarm(@RequestBody AuthVO authVO, @RequestBody FoodVO foodVO) {
		try {
			bakeryService.setAlarm(authVO, foodVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("/bakery/useAlarm")
	public Object useAlarm(@RequestBody AuthVO authVO) {
		System.out.println("Controller: useAlarm");
		List<FoodVO> list = bakeryService.useAlarm(authVO);
		return gson.toJson(list);
	}

	@RequestMapping("/bakery/deleteAlarm")
	public Object deleteAlarm(@RequestBody AuthVO authVO, @RequestBody FoodVO foodVO) {
		return gson.toJson(bakeryService.deleteAlarm(authVO, foodVO));
	}

	@RequestMapping("/bakery/searchFood")
	public Object searchFood(@RequestBody FoodVO foodVO) {
		return gson.toJson(bakeryService.searchFood(foodVO));
	}

}
