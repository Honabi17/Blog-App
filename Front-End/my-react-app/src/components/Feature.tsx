import { FaPenNib, FaCog, FaUsers } from "react-icons/fa";


export default function Features(){
    return(
        <section 
            id="features"
            className="features"
        >
            <div className="feature-card">
                <FaPenNib className="feature-icon" />
                <h3>Easy Writing</h3>
                <p>Write without distractions.</p>
            </div>
            <div className="feature-card">
                <FaCog className="feature-icon"/>
                <h3>Customizable</h3>
                <p>Personalize your space.</p>
            </div>
            <div className="feature-card">
                <FaUsers className="feature-icon"/>
                <h3>Connect & Share</h3>
                <p>Share your stories with the world.</p>
            </div>
        </section>
    );
}