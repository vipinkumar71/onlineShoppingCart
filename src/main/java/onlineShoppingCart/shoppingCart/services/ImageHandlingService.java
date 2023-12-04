package onlineShoppingCart.shoppingCart.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
@Transactional
public interface ImageHandlingService {
    String uploadImage(Long empId, MultipartFile imageFile) throws IOException;
    byte[] serveImage(Long empId) throws IOException;
}