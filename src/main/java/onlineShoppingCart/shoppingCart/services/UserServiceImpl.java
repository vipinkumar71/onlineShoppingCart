package onlineShoppingCart.shoppingCart.services;

import onlineShoppingCart.shoppingCart.customException.ElementNotFountException;
import onlineShoppingCart.shoppingCart.customException.UserAlreadyExistsException;
import onlineShoppingCart.shoppingCart.dto.UsersDTO;
import onlineShoppingCart.shoppingCart.dto.UsersRespDTO;
import onlineShoppingCart.shoppingCart.entities.Carts;
import onlineShoppingCart.shoppingCart.entities.Role;
import onlineShoppingCart.shoppingCart.entities.Users;
import onlineShoppingCart.shoppingCart.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
        @Autowired
        private UserRepository userRepo;

        @Autowired
        private CartService cartService;

        @Autowired
        private OrderService orderService;

        @Autowired
        private ModelMapper mapper;

        @Autowired
        private PasswordEncoder enc;

        @Override
        public UsersRespDTO addUserDetails(UsersDTO user) {
            Users trueUser = new Users(user.getFirstName(), user.getLastName(), user.getEmail(),
                    enc.encode(user.getPassword()), user.getRole(), user.getMobileNumber());
            Optional<Users> checkUser = userRepo.findByEmail(user.getEmail());

            System.out.println("++++++++++++++++++++++++++++++++++" + checkUser);

            if (checkUser.isPresent() == true) {
                throw new UserAlreadyExistsException("User Email already exists!!!!!");
            }

            Users addedUser = userRepo.save(trueUser);
            if (addedUser.getRole() == Role.CUSTOMER) {
                Carts cart = cartService.addCart(addedUser);
                addedUser.setCart(cart);
                return mapper.map(addedUser, UsersRespDTO.class);
            } else {
                return mapper.map(addedUser, UsersRespDTO.class);
            }

        }

        @Override
        public String deleteUserDetails(Long userId) {
            cartService.emptyTheCart(userId);
            orderService.deleteOrders(userId);
            userRepo.deleteById(userId);
            return "User is Deleted";
        }

        @Override
        public Users updateUserDetails(UsersDTO user) {
            Optional<Users> oldUser = userRepo.findByEmail(user.getEmail());
            if (oldUser == null) {
                throw new ElementNotFountException("User", "404", "Not Found");
            }
            BeanUtils.copyProperties(user, oldUser);
            return oldUser.get();
        }

        @Override
        public List<Users> getAllUsers() {
            return userRepo.findAll();
        }

    }
