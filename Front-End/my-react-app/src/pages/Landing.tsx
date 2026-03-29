import DashboardPreview from "../components/DashboardPreview";
import Features from "../components/Feature";
import Footer from "../components/Footer";
import Hero from "../components/Hero";
import Navbar from "../components/navbar/Navbar";
import '../styles/Landing.css';


export default function Landing(){

    const scrollToSection = (id: string) => {
        const section = document.getElementById(id);
            if(section){
                section.scrollIntoView({behavior: "smooth"});
            }
    }
    return(
        <div className="landing-page">
            <Navbar />
            <Hero  scrollToSection={scrollToSection}/>
            <Features />
            <DashboardPreview />
            <Footer />
        </div>
    );
}