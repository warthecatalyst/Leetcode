package Pro2383;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    static class Element implements Comparable<Element>{
        int energy;
        int experience;

        public Element(int energy,int experience){
            this.energy = energy;
            this.experience = experience;
        }

        @Override
        public int compareTo(Element o) {
            return Integer.compare(experience,o.experience);
        }
    }

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int n = energy.length;
        Element[] elements = new Element[n];
        for(int i = 0;i<n;i++){
            elements[i] = new Element(energy[i],experience[i]);
        }
        Arrays.sort(elements);
        int energyAdd = 0, experienceAdd = 0;
        int curEnergy = initialEnergy, curExperience = initialExperience;
        for(Element element:elements){
            if(curEnergy<element.energy){
                energyAdd += element.energy-curEnergy;
                curEnergy = element.energy;
            }
            if(curExperience<element.experience){
                experienceAdd += element.experience-curExperience;
                curExperience = element.experience;
            }
            curEnergy -= element.energy;
            curExperience += element.experience;
        }
        return energyAdd+experienceAdd;
    }
}
