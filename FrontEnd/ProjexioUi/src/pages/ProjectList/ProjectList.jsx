import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { ScrollArea } from "@/components/ui/scroll-area";
import {
  MagnifyingGlassIcon,
  MixerHorizontalIcon,
} from "@radix-ui/react-icons";
import React, { useState } from "react";
import ProjectCard from "../Project/ProjectCard";

export const tags = [
  "all",
  "React",
  "NextJS",
  "Mysql",
  "Spring Boot",
  "Angular",
  "MongoDb",
  "Flask",
  "Python",
  "Flask",
  "Django",
];

const ProjectList = () => {
  const [keyword, setKeyword] = useState("");

  const handleFilterChange = (section, value) => {
    console.log("Filter value:", value, section);
  };

  const handleSearchChange = (e) => {
    setKeyword(e.target.value);
  };

  return (
    <div className="relative px-5 lg:px-0 lg:flex gap-5 justify-center py-5">
      {/* Filter Section */}
      <section className="filterSection">
        <Card className="p-5 sticky top-10">
          <div className="flex justify-between lg:w-[20rem]">
            <p className="text-xl tracking-wider">Filters</p>
            <Button variant="ghost" size="icon">
              <MixerHorizontalIcon />
            </Button>
          </div>

          <CardContent className="mt-5">
            <ScrollArea className="space-y-7 h-[70vh]">
              <div>
                <h1 className="pb-3 text-gray-400 border-b">Category</h1>
              </div>
              <div className="pt-5">
                <RadioGroup
                  className="space-y-7 pt-5"
                  defaultValue="all"
                  onValueChange={handleFilterChange}
                >
                  <div className="flex items-center gap-2">
                    <RadioGroupItem value="all" id="r1" />
                    <Label htmlFor="r1">All</Label>
                  </div>
                  <div className="flex items-center gap-2">
                    <RadioGroupItem value="fullstack" id="r2" />
                    <Label htmlFor="r2">Fullstack</Label>
                  </div>
                  <div className="flex items-center gap-2">
                    <RadioGroupItem value="frontend" id="r3" />
                    <Label htmlFor="r3">Frontend</Label>
                  </div>
                  <div className="flex items-center gap-2">
                    <RadioGroupItem value="backend" id="r4" />
                    <Label htmlFor="r4">Backend</Label>
                  </div>
                </RadioGroup>
              </div>

              <div className="pt-9">
                <h1 className="pb-3 text-gray-400 border-b">Tag</h1>
                <RadioGroup
                  className="space-y-7 pt-5"
                  defaultValue="all"
                  onValueChange={(value) => handleFilterChange("tag", value)}
                >
                  {tags.map((item) => (
                    <div key={item} className="flex items-center gap-2 ">
                      <RadioGroupItem value={item} id={`r1-${item}`} />
                      <Label htmlFor={`r1-${item}`}>{item}</Label>
                    </div>
                  ))}
                </RadioGroup>
              </div>
            </ScrollArea>
          </CardContent>
        </Card>
      </section>

      {/* Search Bar and Project List */}
      <section className="w-full lg:w-[48rem]">
        {/* Search Bar */}
        <div className="flex gap-2 items-center pb-5">
          <div className="relative p-0 w-full">
            <Input
              onChange={handleSearchChange}
              placeholder="Search Project"
              className="40% px-9"
            />
            <MagnifyingGlassIcon className="absolute top-3 left-4" />
          </div>
        </div>

        {/* Project Cards */}
        <div className="space-y-5">
          {keyword
            ? [1, 1, 1].map((item) => <ProjectCard key={item} />)
            : [1, 1, 1, 1].map((item) => <ProjectCard key={item} />)}
        </div>
      </section>
    </div>
  );
};

export default ProjectList;
