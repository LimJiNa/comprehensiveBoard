package com.comprehensive.board;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.comprehensive.boardFile.BoardFilesService;
import com.comprehensive.boardFile.BoardFilesVO;
import com.comprehensive.common.FileUtil;
import com.comprehensive.common.PageMaker;
import com.comprehensive.common.SearchCriteria;

@Controller
public class BoardController {

	private Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardFilesService boardFilesService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGET(@ModelAttribute("cri") SearchCriteria cri, Model model) {
		logger.info("cri =======> " + cri.toString());
		model.addAttribute("data", boardService.list(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.listCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public void registerGET(SearchCriteria cri, Model model) {
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPageNum", cri.getPerPageNum());
		for (int i = 0; i < cri.getSearchType().length; i++) {
			model.addAttribute("searchType", cri.getSearchType()[i]);
		}
		model.addAttribute("keyword", cri.getKeyword());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardWriteDTO writeDto, RedirectAttributes rttr) throws Exception {

		int result = boardService.register(writeDto);
		
		if (result == 1) {
			rttr.addFlashAttribute("msg", "success");
		}

		return "redirect:/list";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void one(int seq, SearchCriteria cri, Model model) {
		boardService.updateHit(seq);
		model.addAttribute("data", boardService.findOne(seq));
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPageNum", cri.getPerPageNum());
		for (int i = 0; i < cri.getSearchType().length; i++) {
			model.addAttribute("searchType", cri.getSearchType()[i]);
		}
		model.addAttribute("keyword", cri.getKeyword());
	}

	@RequestMapping(value = "/download/{fileSeq}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable("fileSeq") int fileSeq) throws IOException {
		logger.info("fileSeq =======> " + fileSeq);

		BoardFilesVO boardFilesVo = new BoardFilesVO();
		boardFilesVo = boardFilesService.findOne(fileSeq);
		File file = new File(FileUtil.UPLOAD_PATH + "/" + boardFilesVo.getFileName());

		logger.info("boardFilesVo =======> " + boardFilesVo.getFileName());

		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}

//		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//		if (mimeType == null) {
//			System.out.println("mimetype is not detectable, will take default");
//			mimeType = "application/octet-stream";
//		}
//		System.out.println("mimeType : " + mimeType);

		response.setContentType("application/octet-stream");
		response.setHeader("content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

	@RequestMapping(value = "/pwCheck", method = RequestMethod.POST)
	public String modifyPwCheck(BoardPwCheckDTO boardPwCheckDto, RedirectAttributes rttr) {
		BoardVO boardVo = new BoardVO();
		boardVo.setSeq(boardPwCheckDto.getSeq());
		boardVo.setPassword(boardPwCheckDto.getPassword());
		int result = boardService.checkPw(boardVo);
		if ("modify".equals(boardPwCheckDto.getActionType())) {
			if (result == 1) {
				rttr.addFlashAttribute("msg", "success");
				rttr.addAttribute("seq", boardPwCheckDto.getSeq());
				return "redirect:/modifyForm";
			} else {
				rttr.addFlashAttribute("msg", "fail");
				rttr.addAttribute("seq", boardPwCheckDto.getSeq());
				return "redirect:/detail";
			}
		} else if ("remove".equals(boardPwCheckDto.getActionType()) && result != 1) {
			rttr.addFlashAttribute("msg", "fail");
			rttr.addAttribute("seq", boardPwCheckDto.getSeq());
			return "redirect:/detail";
		}

		if ("remove".equals(boardPwCheckDto.getActionType())) {
			BoardFilesVO boardFilesVo = new BoardFilesVO();
			boardFilesVo.setBoardSeq(boardPwCheckDto.getSeq());
			boardFilesVo.setFileSeq(boardPwCheckDto.getFileSeq());

			result = boardService.remove(boardFilesVo);

			if (result == 1) {
				rttr.addFlashAttribute("msg", "success");
				return "redirect:/list";
			} else {
				rttr.addFlashAttribute("msg", "fail");
				rttr.addAttribute("seq", boardPwCheckDto.getSeq());
				return "redirect:/detail";
			}

		}

		return "redirect:/list";
	}

	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public void modifyFormGET(@RequestParam("seq") int seq, @ModelAttribute("cri") SearchCriteria cri, Model model) {
		logger.info("seq =======> " + seq);

		model.addAttribute("data", boardService.findOne(seq));
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPageNum", cri.getPerPageNum());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardModifyDTO boardModifyDto, @ModelAttribute("cri") SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {
		BoardVO boardVo = new BoardVO();
		boardVo.setSeq(boardModifyDto.getSeq());
		boardVo.setTitle(boardModifyDto.getTitle());
		boardVo.setWriter(boardModifyDto.getWriter());
		boardVo.setPassword(boardModifyDto.getPassword());
		boardVo.setContent(boardModifyDto.getContent());

		BoardFilesVO boardFilesVo = new BoardFilesVO();
		boardFilesVo.setFileSeq(boardModifyDto.getFileSeq());
		int result = boardService.modify(boardVo, boardFilesVo, boardModifyDto.getUploadFile());

		if (result == 1) {
			rttr.addFlashAttribute("msg", "success");
		} else {
			rttr.addFlashAttribute("msg", "fail");
		}

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/list";
	}

}
