type HeroProps = {
    scrollToSection: (id: string) => void;
}

export default function Hero({scrollToSection}: HeroProps){
    return(
        <section className="hero">
            <h1 className="hero-title"> Write. Share. Inspire.</h1>
            <p className="hero-subtitle">Your platform to express yourself freely.</p>
        </section>
    );
}