package net.microflax.skylink.review.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.review.Review;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemReviewController")
@RequestMapping("/system/review")
@DataSet(model = Review.class, timeFilter = false)
public class ReviewController extends DataSetController<Review, Integer> {
}
