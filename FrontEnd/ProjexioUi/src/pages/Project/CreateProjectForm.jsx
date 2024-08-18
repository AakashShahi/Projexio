import { Form, FormItem, FormControl, FormMessage, FormField } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { useForm } from "react-hook-form";
import { Button } from "@/components/ui/button"; // Make sure Button is imported
import { Select, SelectTrigger, SelectItem, SelectContent, SelectValue } from "@/components/ui/select";
import { tags } from "../ProjectList/ProjectList";
import { Cross1Icon } from "@radix-ui/react-icons";
import { DialogClose } from "@/components/ui/dialog";

const CreateProjectForm = () => {

    const handleTagsChange=(newValue)=>{
        const currentTags=form.getValues("tags");

        const updatedTags=currentTags.includes(newValue)?
        currentTags.filter(tag=>tag!==newValue):
        [...currentTags,newValue];

        form.setValue("tags",updatedTags)
    }

  const form = useForm({
    defaultValues: {
      name: "",
      description: "",
      category: "",
      tags: ["react"],
    },
  });

  const onSubmit = (data) => {
    console.log(data);
  };

  return (
    <div className="p-5">
      <Form {...form}>
        <form className="space-y-5" onSubmit={form.handleSubmit(onSubmit)}>
          {/* Name Field */}
          <FormField
            control={form.control}
            name="name"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Input
                    {...field}
                    type="text"
                    className="border w-full border-gray-700 py-2 px-4"
                    placeholder="Project Name..."
                  />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          {/* Description Field */}
          <FormField
            control={form.control}
            name="description"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Input
                    {...field}
                    type="text"
                    className="border w-full border-gray-700 py-5 px-5"
                    placeholder="Project Description"
                  />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

          {/* Category Select Field */}
          <FormField
            control={form.control}
            name="category"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Select
                    value={field.value}
                    onValueChange={field.onChange}
                    defaultValue="fullstack"
                  >
                    <SelectTrigger className="w-full">
                       <SelectValue placeholder="Category"/>
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="fullstack">Full stack</SelectItem>
                      <SelectItem value="frontend">Front End</SelectItem>
                      <SelectItem value="backend">Back End</SelectItem>
                    </SelectContent>
                  </Select>
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

        <FormField
            control={form.control}
            name="tags"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Select
                    onValueChange={(value)=>{
                        handleTagsChange(value)
                    }}

                  > 
                    <SelectTrigger className="w-full">
                       <SelectValue placeholder="Tags"/>
                    </SelectTrigger>
                    <SelectContent>
                     {tags.map((item)=>(
                     <SelectItem key={item} value={item}>{item}
                     </SelectItem> 
                    ))} 
                    </SelectContent>
                    <div className="flex gap-1 flex-wrap">

                        {field.value.map((item)=><div key={item} onClick={()=>handleTagsChange(item)} className="cursor-pointer flex rounded-full 
                        items-center border gap-1 px-4 py-1">
                            <span className="text-sm">
                                {item}
                            </span>
                            <Cross1Icon className="h-3 w-3"/>
                        </div>)}

                    </div>
                  </Select> 
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />


          <DialogClose>
            {false?(
                <div>
                    <p>
                        You can only create 3 project with free plan
                        Please Upgrade your plan
                    </p>
                </div>
            ):(
                <Button type="submit" className="w-full mt-5">
                    Create Project
                </Button>
            )}
          </DialogClose>
        </form>
      </Form>
    </div>
  );
};

export default CreateProjectForm;
