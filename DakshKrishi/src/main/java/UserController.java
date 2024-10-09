

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thyme.exceptions.ResourceNotFoundException;
import com.thyme.payloads.UserDto;
import com.thyme.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Serve the signup HTML page
    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("userData", new UserDto());
        return "signup";  // Return the Thymeleaf template for signup
    }

    // Serve the login HTML page
    @GetMapping("/login")
    public String loginUser(Model model) {
        model.addAttribute("userData", new UserDto());
        return "login";  // Return the Thymeleaf template for login
    }

    // Handle registration form submission
    @PostMapping("/do_register")
    public String createUser(
            @Valid @ModelAttribute("userData") UserDto userDto,
            BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("userData", userDto);
            return "signup"; // Return signup page with validation errors
        }

        userService.createUser(userDto);  // Create new user
        redirectAttributes.addFlashAttribute("message", "Registration successful! Please log in.");
        return "redirect:/users/login"; // Redirect to login page
    }

    // Handle login form submission
    @PostMapping("/do_login")
    public String getUserByNamePass(
            @Valid @ModelAttribute("userData") UserDto userDto,
            BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        // Check if form validation has errors
        if (result.hasErrors()) {
            model.addAttribute("userData", userDto);
            return "login"; // Return login page with validation errors
        }

        try {
            // Authenticate user with the provided username and password
            logger.info("Authenticating user: {}", userDto.getName());
            UserDto authenticatedUser = userService.authenticateUser(userDto.getName(), userDto.getPassword());

            if (authenticatedUser == null) {
                throw new IllegalArgumentException("Invalid username or password");
            }

            // Set authenticated user in session
            session.setAttribute("loggedInUser", authenticatedUser);
            logger.info("User logged in: {}", authenticatedUser.getName());
            return "redirect:/users/home"; // Redirect to home page on successful login
        } catch (ResourceNotFoundException | IllegalArgumentException ex) {
            // Handle the case where user is not found or password is incorrect
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            logger.warn("Login failed: {}", ex.getMessage());
            return "redirect:/users/login"; // Redirect back to login page with error message
        }
    }

    // Serve the home page
    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        // Get the logged-in user from the session
        UserDto loggedInUser = (UserDto) session.getAttribute("loggedInUser");

        // Add username to model if user is logged in
        model.addAttribute("username", loggedInUser != null ? loggedInUser.getName() : null);
        logger.info("Logged in user: {}", loggedInUser != null ? loggedInUser.getName() : "null");
        return "home"; // Return the home page view
    }

    // Handle user logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        logger.info("User logged out");
        return "redirect:/users/login"; // Redirect to the login page
    }
}
